package com.example.data.models

data class NotificationDto(
    val title: String? = null,
    val description: String? = null,
    var hasBeenRead: Boolean = false,
    var userId: String? = null,
    var firestoreId: String? = null,
    val created: String? = null
)