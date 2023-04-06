package com.example.domain.combiners

import com.example.domain.models.DashboardModel
import com.example.domain.models.Result
import com.example.domain.repositories.DashboardRepository
import com.example.domain.repositories.ProfileRepository
import com.example.domain.repositories.RatingsRepository
import com.example.domain.repositories.VetReponsitory
import javax.inject.Inject

class GetDashboardDataCombiner @Inject constructor(
    private val dashboardRepo: DashboardRepository,
    private val profileRepo: ProfileRepository,
    private val vetsRepo: VetReponsitory,
    private val ratingRepo: RatingsRepository
) {
    suspend fun getDashboardData(uuid: String): Result<DashboardModel> {
        val profileData = profileRepo.getProfileInfo(uuid)
        val ratings = ratingRepo.getAllRatings()
        val vets = vetsRepo.getAllVets()

        val groupedRatings = ratings.groupBy { it.doctorId }
        val mappedVets = vets.apply {
            map {
                it.rating = groupedRatings[it.id]?.map { it.value }?.average() ?: 0.0
            }
        }

        val dashboardData = dashboardRepo.getDashboardData().apply {
            userInfo = profileData
            popularVets.items = mappedVets
        }

        return Result.Success(dashboardData)
    }
}