package com.example.domain.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Appointment(
    val monthName: String = LocalDateTime.now().month.toString(),
    val date: LocalDate,
    val timeslot: String
) {
    fun toFirestoreFormat(): LocalDateTime? {
        val datePart: LocalDate = LocalDate.parse(date.toString())
        val timePart: LocalTime = LocalTime.parse(timeslot)
        return LocalDateTime.of(datePart, timePart)
    }
}
