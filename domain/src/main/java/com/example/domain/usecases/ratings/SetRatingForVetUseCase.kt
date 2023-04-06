package com.example.domain.usecases.ratings

import com.example.domain.models.Rating
import com.example.domain.repositories.RatingsRepository
import javax.inject.Inject

class SetRatingForVetUseCase @Inject constructor(
    private val repo: RatingsRepository
) {
    suspend fun setRating(rating: Rating) = repo.setRating(rating)
}