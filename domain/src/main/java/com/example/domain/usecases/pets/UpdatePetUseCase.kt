package com.example.domain.usecases.pets

import com.example.domain.models.pets.Pet
import com.example.domain.models.pets.UpdatePetRequest
import com.example.domain.repositories.PetRepository
import javax.inject.Inject

class UpdatePetUseCase @Inject constructor(
    private val repo: PetRepository
) {
    suspend fun updatePet(original: Pet?, pet: UpdatePetRequest, uuid: String) =
        repo.updatePet(original, pet, uuid)
}