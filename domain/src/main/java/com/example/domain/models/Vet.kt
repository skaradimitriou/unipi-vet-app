package com.example.domain.models

data class Vet(
    val category: String,
    val experience: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val image: String,
    val mobileNo: String,
    val rating: Double
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = false
}
