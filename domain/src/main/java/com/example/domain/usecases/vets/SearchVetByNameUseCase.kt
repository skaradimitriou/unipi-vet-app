package com.example.domain.usecases.vets

import com.example.domain.models.Vet
import com.example.domain.repositories.VetReponsitory
import javax.inject.Inject

class SearchVetByNameUseCase @Inject constructor(
    private val repo: VetReponsitory
) {
    suspend fun searchByName(name: String): List<Vet> = repo.searchByName(name)
}