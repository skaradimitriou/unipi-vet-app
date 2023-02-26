package com.example.domain.models

import java.time.LocalDate
import java.time.LocalDateTime

data class Appointment(
    val monthName: String = LocalDateTime.now().month.toString(),
    val date: LocalDate,
    val timeslot: String
)
