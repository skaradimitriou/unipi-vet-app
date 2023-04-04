package com.example.data.repositories

import com.example.data.mappers.VetMapper
import com.example.data.models.VetDto
import com.example.data.util.VETS_DB_PATH
import com.example.data.util.toListOf
import com.example.domain.models.Vet
import com.example.domain.repositories.VetReponsitory
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class VetRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : VetReponsitory {

    override suspend fun getAllVets(): List<Vet> {
        val result = firestore.collection(VETS_DB_PATH)
            .get()
            .await()
            .toListOf<VetDto>()

        return VetMapper.toDomainModel(result)
    }

    override suspend fun searchByName(name: String): List<Vet> {
        val result = firestore.collection(VETS_DB_PATH)
            .get()
            .await()
            .toListOf<VetDto>()

        val mappedData = VetMapper.toDomainModel(result)
        return mappedData.toMutableList().filter { it.fullName.contains(name) }
    }
}