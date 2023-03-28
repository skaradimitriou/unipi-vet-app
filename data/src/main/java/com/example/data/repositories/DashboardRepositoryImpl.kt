package com.example.data.repositories

import com.example.data.mappers.DashboardMapper
import com.example.data.models.DashboardModelDto
import com.example.data.util.DASHBOARD_DB_PATH
import com.example.data.util.DATA
import com.example.domain.models.DashboardModel
import com.example.domain.repositories.DashboardRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : DashboardRepository {

    override suspend fun getDashboardData(): DashboardModel {
        val result = firestore.collection(DASHBOARD_DB_PATH)
            .document(DATA)
            .get()
            .await()
            .toObject(DashboardModelDto::class.java)

        return DashboardMapper.toDomainModel(result)
    }
}