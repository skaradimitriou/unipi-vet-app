package com.example.data.repositories

import com.example.data.mappers.PetMapper
import com.example.data.models.PetResponseDto
import com.example.data.util.PETS_DB_PATH
import com.example.domain.models.Pet
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
}