package com.example.domain.repositories

import com.example.domain.models.Appointment
import com.example.domain.models.AppointmentInfo
import com.example.domain.models.Result
import com.example.domain.models.Vet

interface AppointmentRepository {

    suspend fun saveAppointment(appointment: Appointment, vet: Vet, userId: String): Result<Boolean>

    suspend fun getMyAppointments(uuid: String): Result<List<AppointmentInfo>>

    suspend fun deleteAppointment(appointment: AppointmentInfo, userId: String): Result<Boolean>
}