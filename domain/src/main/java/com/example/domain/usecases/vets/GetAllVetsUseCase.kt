package com.example.domain.usecases.vets

import com.example.domain.models.Vet
import com.example.domain.repositories.VetReponsitory
import javax.inject.Inject

class GetAllVetsUseCase @Inject constructor(
    private val repo: VetReponsitory
) {
    suspend fun getAllVets(): List<Vet> = repo.getAllVets()
}