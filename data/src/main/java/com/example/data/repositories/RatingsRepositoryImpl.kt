package com.example.data.repositories

import com.example.data.mappers.RatingsMapper
import com.example.data.models.RatingDto
import com.example.data.util.DOC_ID
import com.example.data.util.RATINGS_DB_PATH
import com.example.data.util.toListOf
import com.example.domain.models.Rating
import com.example.domain.repositories.RatingsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RatingsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : RatingsRepository {

    override suspend fun getAllRatings(docId: Int): List<Rating> {
        val result = firestore.collection(RATINGS_DB_PATH)
            .whereEqualTo(DOC_ID, docId)
            .get()
            .await()
            .toListOf<RatingDto>()

        return RatingsMapper.toDomainModel(result)
    }
}