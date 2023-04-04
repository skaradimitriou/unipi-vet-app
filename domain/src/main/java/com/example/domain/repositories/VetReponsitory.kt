package com.example.domain.repositories

import com.example.domain.models.Vet

interface VetReponsitory {

    suspend fun getAllVets(): List<Vet>

    suspend fun searchByName(name: String): List<Vet>
}