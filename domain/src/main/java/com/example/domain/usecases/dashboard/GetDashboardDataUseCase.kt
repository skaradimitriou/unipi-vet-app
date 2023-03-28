package com.example.domain.usecases.dashboard

import com.example.domain.models.DashboardModel
import com.example.domain.models.Result
import com.example.domain.repositories.DashboardRepository
import com.example.domain.repositories.ProfileRepository
import javax.inject.Inject

class GetDashboardDataUseCase @Inject constructor(
    private val dashboardRepo: DashboardRepository,
    private val profileRepo: ProfileRepository
) {
    suspend fun getDashboardData(uuid: String): Result<DashboardModel> {
        val profileData = profileRepo.getProfileInfo(uuid)
        val dashboardData = dashboardRepo.getDashboardData().apply {
            userInfo = profileData
        }
        return Result.Success(dashboardData)
    }
}