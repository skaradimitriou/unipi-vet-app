package com.example.domain.repositories

import com.example.domain.models.Notification

interface NotificationsRepository {

    fun setNewNotification(notification: Notification, uuid: String)

    suspend fun getAllNotifications(uuid: String): List<Notification>

    suspend fun hasUnreadNotifications(uuid: String): Boolean

    suspend fun readAllNotifications(uuid: String)
}