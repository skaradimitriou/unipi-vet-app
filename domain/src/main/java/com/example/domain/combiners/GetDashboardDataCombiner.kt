package com.example.domain.combiners

import com.example.domain.models.DashboardModel
import com.example.domain.models.Result
import com.example.domain.repositories.*
import javax.inject.Inject

class GetDashboardDataCombiner @Inject constructor(
    private val dashboardRepo: DashboardRepository,
    private val profileRepo: ProfileRepository,
    private val vetsRepo: VetReponsitory,
    private val ratingRepo: RatingsRepository,
    private val notificationsRepo: NotificationsRepository
) {
    suspend fun getDashboardData(uuid: String): Result<DashboardModel> {
        val profileData = profileRepo.getProfileInfo(uuid)
        val ratings = ratingRepo.getAllRatings()
        val vets = vetsRepo.getAllVets()
        val hasUnreadNotifications = notificationsRepo.hasUnreadNotifications(uuid)

        val groupedRatings = ratings.groupBy { it.doctorId }
        val mappedVets = vets.apply {
            map {
                it.rating = groupedRatings[it.id]?.map { it.value }?.average() ?: 0.0
            }
        }.take(5)

        val dashboardData = dashboardRepo.getDashboardData().apply {
            userInfo = profileData
            userInfo.hasUnreadNotifiactions = hasUnreadNotifications
            popularVets.items = mappedVets
        }

        return Result.Success(dashboardData)
    }
}