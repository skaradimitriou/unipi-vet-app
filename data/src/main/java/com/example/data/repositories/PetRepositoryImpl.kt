package com.example.data.repositories

import com.example.data.mappers.PetMapper
import com.example.data.models.PetResponseDto
import com.example.data.util.PETS_DB_PATH
import com.example.data.util.compressBitmap
import com.example.domain.models.Result
import com.example.domain.models.pets.Pet
import com.example.domain.models.pets.PetRequest
import com.example.domain.models.pets.UpdatePetRequest
import com.example.domain.repositories.PetRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: StorageReference
) : PetRepository {

    override suspend fun getMyPets(uuid: String): List<Pet> {
        val result = firestore.collection(PETS_DB_PATH)
            .document(uuid)
            .get()
            .await()
            .toObject(PetResponseDto::class.java)

        return PetMapper.toDomainModel(result)
    }

    override suspend fun addNewPet(pet: PetRequest, uuid: String): Result<Boolean> {
        var result: Result<Boolean> = Result.Loading()

        val list = getMyPets(uuid).toMutableList()

        val storageRef = storage.child("pets/$uuid/${pet.nickname}")
        val downloadUrl = pet.image?.compressBitmap()?.let {
            val upload = storageRef.putBytes(it).await()
            upload.metadata?.reference?.downloadUrl?.await().toString()
        }

        list.add(Pet(pet.nickname ?: "", downloadUrl ?: "", pet.type))

        val data: HashMap<String, Any> = hashMapOf("pets" to list)

        firestore.collection(PETS_DB_PATH)
            .document(uuid)
            .set(data)
            .addOnSuccessListener {
                result = Result.Success(true)
            }
            .addOnFailureListener {
                result = Result.Failure(error = it.message.toString())
            }
            .await()

        return result
    }

    override suspend fun updatePet(
        original: Pet?,
        pet: UpdatePetRequest,
        uuid: String
    ): Result<Boolean> {
        var result: Result<Boolean> = Result.Loading()

        val storageRef = storage.child("pets/$uuid/${pet.nickname}")
        val downloadUrl = pet.image?.compressBitmap()?.let {
            val upload = storageRef.putBytes(it).await()
            upload.metadata?.reference?.downloadUrl?.await().toString()
        }

        val list = getMyPets(uuid).toMutableList().apply {
            map { item ->
                original?.let { currentPet ->
                    if (item.equalsContent(currentPet)) {
                        item.image = downloadUrl.toString()
                        item.nickname = pet.nickname.toString()
                        item.type = pet.type
                    }
                }
            }
        }

        val data: HashMap<String, Any> = hashMapOf("pets" to list)

        firestore.collection(PETS_DB_PATH)
            .document(uuid)
            .set(data)
            .addOnSuccessListener {
                result = Result.Success(true)
            }
            .addOnFailureListener {
                result = Result.Failure(error = it.message.toString())
            }
            .await()

        return result
    }
}