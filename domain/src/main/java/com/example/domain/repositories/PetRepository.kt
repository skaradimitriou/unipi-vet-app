package com.example.domain.repositories

import com.example.domain.models.Result
import com.example.domain.models.pets.Pet
import com.example.domain.models.pets.PetRequest
import com.example.domain.models.pets.UpdatePetRequest

interface PetRepository {

    suspend fun getMyPets(uuid: String): List<Pet>

    suspend fun addNewPet(pet: PetRequest, uuid: String): Result<Boolean>

    suspend fun updatePet(original: Pet?, pet: UpdatePetRequest, uuid: String): Result<Boolean>
}