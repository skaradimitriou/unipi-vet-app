package com.example.data.repositories

import com.example.data.mappers.NotificationsMapper
import com.example.data.models.NotificationDto
import com.example.data.util.NOTIFICATIONS_DB_PATH
import com.example.data.util.toListOf
import com.example.domain.models.Notification
import com.example.domain.repositories.NotificationsRepository
import com.example.domain.utils.HAS_BEEN_READ
import com.example.domain.utils.USER_ID
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : NotificationsRepository {

    override fun setNewNotification(notification: Notification, uuid: String) {
        firestore.collection(NOTIFICATIONS_DB_PATH).document().apply {
            notification.userId = uuid
            notification.firestoreId = id
            set(notification)
        }
    }

    override suspend fun getAllNotifications(uuid: String): List<Notification> {
        val result = firestore.collection(NOTIFICATIONS_DB_PATH)
            .whereEqualTo(USER_ID, uuid)
            .get()
            .await()
            .toListOf<NotificationDto>()

        return NotificationsMapper.toDomainModel(result).sortedByDescending {
            LocalDateTime.parse(it.created)
        }
    }

    override suspend fun hasUnreadNotifications(uuid: String): Boolean {
        val result = firestore.collection(NOTIFICATIONS_DB_PATH)
            .whereEqualTo(USER_ID, uuid)
            .whereEqualTo(HAS_BEEN_READ, false)
            .get()
            .await()
            .toListOf<NotificationDto>()

        return result.isNotEmpty()
    }

    override suspend fun readAllNotifications(uuid: String) {
        val result = firestore.collection(NOTIFICATIONS_DB_PATH)
            .whereEqualTo(USER_ID, uuid)
            .get()
            .await()
            .toListOf<NotificationDto>()

        val mappedData = NotificationsMapper.toDomainModel(result)

        mappedData.forEach {
            firestore.collection(NOTIFICATIONS_DB_PATH)
                .document(it.firestoreId)
                .update(mapOf(HAS_BEEN_READ to true))
        }
    }
}