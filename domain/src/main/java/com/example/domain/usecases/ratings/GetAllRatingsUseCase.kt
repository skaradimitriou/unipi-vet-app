package com.example.domain.usecases.ratings

import com.example.domain.repositories.RatingsRepository
import javax.inject.Inject

class GetAllRatingsUseCase @Inject constructor(
    private val repo: RatingsRepository
) {
    suspend fun getAllRatings(docId: Int) = repo.getAllRatings(docId)
}