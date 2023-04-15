package com.example.domain.models

import java.time.LocalDateTime

data class Notification(
    val title: String,
    val description: String,
    var hasBeenRead: Boolean = false,
    var userId: String = "",
    var firestoreId: String = "",
    val created: String = LocalDateTime.now().toString()
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is Notification -> title == obj.title && description == obj.description && hasBeenRead == obj.hasBeenRead
        else -> false
    }
}
