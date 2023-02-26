package com.example.domain.models

data class AppointmentInfo(
    val vet: Vet,
    val appointmentDateAndTime: String,
    val uuid: String
)
