package com.example.domain.usecases.notifications

import com.example.domain.repositories.NotificationsRepository
import javax.inject.Inject

class ReadAllNotificationsUseCase @Inject constructor(
    private val repo: NotificationsRepository
) {
    suspend fun readAllNotifications(uuid: String) = repo.readAllNotifications(uuid)
}