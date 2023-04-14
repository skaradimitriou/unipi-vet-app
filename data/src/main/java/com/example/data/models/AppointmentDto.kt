package com.example.data.models

data class AppointmentDto(
    val vet: VetDto? = null,
    val appointmentDateAndTime: String? = null,
    val uuid: String? = null,
    val firestoreId: String? = null
)
