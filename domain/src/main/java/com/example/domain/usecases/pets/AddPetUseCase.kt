package com.example.domain.usecases.pets

import com.example.domain.models.PetRequest
import com.example.domain.repositories.PetRepository
import javax.inject.Inject

class AddPetUseCase @Inject constructor(
    private val repo: PetRepository
) {
    suspend fun addNewPet(pet: PetRequest, uuid: String) = repo.addNewPet(pet, uuid)
}