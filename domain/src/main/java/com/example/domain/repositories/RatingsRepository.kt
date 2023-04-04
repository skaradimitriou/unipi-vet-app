package com.example.domain.repositories

import com.example.domain.models.Rating

interface RatingsRepository {

    suspend fun getAllRatings(docId : Int): List<Rating>
}