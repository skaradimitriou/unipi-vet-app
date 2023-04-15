package com.example.data.mappers

import com.example.data.models.NotificationDto
import com.example.data.util.toNotNull
import com.example.domain.models.Notification

object NotificationsMapper : Mapper<List<NotificationDto>?, List<Notification>> {

    override fun toDomainModel(dto: List<NotificationDto>?): List<Notification> {
        return dto?.map {
            Notification(
                title = it.title.toNotNull(),
                description = it.description.toNotNull(),
                hasBeenRead = it.hasBeenRead.toNotNull(),
                userId = it.userId.toNotNull(),
                firestoreId = it.firestoreId.toNotNull(),
                created = it.created.toNotNull()
            )
        } ?: listOf()
    }
}