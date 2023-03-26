package com.example.domain.usecases.appointments

import com.example.domain.models.Appointment
import com.example.domain.models.Result
import com.example.domain.models.Vet
import com.example.domain.repositories.AppointmentRepository
import javax.inject.Inject

class StoreAppointmentUseCase @Inject constructor(
    private val repo: AppointmentRepository
) {
    suspend fun saveAppointment(appointment: Appointment, vet: Vet, userId: String): Result<Boolean> {
        return repo.saveAppointment(appointment, vet, userId)
    }
}