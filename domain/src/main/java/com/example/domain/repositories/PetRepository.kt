package com.example.domain.repositories

import com.example.domain.models.Pet
import com.example.domain.models.PetRequest
import com.example.domain.models.Result

interface PetRepository {

    suspend fun getMyPets(uuid: String): List<Pet>

    suspend fun addNewPet(pet: PetRequest, uuid: String): Result<Boolean>
}