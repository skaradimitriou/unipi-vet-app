package com.example.domain.models

data class Rating(
    val title: String,
    val description: String,
    val value: Double,
    val doctorId: Int
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is Rating -> title == obj.title && description == obj.description && value == obj.value
        else -> false
    }
}
