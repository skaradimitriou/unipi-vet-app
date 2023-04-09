package com.example.domain.repositories

import com.example.domain.models.Pet

interface PetRepository {

    suspend fun getMyPets(uuid: String): List<Pet>
}