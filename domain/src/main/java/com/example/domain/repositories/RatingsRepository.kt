package com.example.domain.repositories

import com.example.domain.models.Rating

interface RatingsRepository {

    suspend fun getAllRatings(): List<Rating>

    suspend fun getAllRatingsById(docId: Int): List<Rating>

    suspend fun setRating(rating: Rating, uuid: String)
}