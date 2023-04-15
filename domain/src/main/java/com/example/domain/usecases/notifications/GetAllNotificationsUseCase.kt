package com.example.domain.usecases.notifications

import com.example.domain.repositories.NotificationsRepository
import javax.inject.Inject

class GetAllNotificationsUseCase @Inject constructor(
    private val repo: NotificationsRepository
) {
    suspend fun getMyNotifications(uuid: String) = repo.getAllNotifications(uuid)
}