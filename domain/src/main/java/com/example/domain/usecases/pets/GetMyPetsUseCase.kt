package com.example.domain.usecases.pets

import com.example.domain.models.Pet
import com.example.domain.repositories.PetRepository
import javax.inject.Inject

class GetMyPetsUseCase @Inject constructor(
    private val repo : PetRepository
) {
    suspend fun getMyPets(uuid : String) : List<Pet> = repo.getMyPets(uuid)
}