package com.example.domain.models

data class AppointmentInfo(
    val vet: Vet,
    val appointmentDateAndTime: String,
    val uuid: String,
    val firestoreId: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is AppointmentInfo -> appointmentDateAndTime == obj.appointmentDateAndTime
        else -> false
    }
}
