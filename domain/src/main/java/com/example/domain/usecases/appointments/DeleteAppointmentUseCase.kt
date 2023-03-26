package com.example.domain.usecases.appointments

import com.example.domain.models.AppointmentInfo
import com.example.domain.models.Result
import com.example.domain.repositories.AppointmentRepository
import javax.inject.Inject

class DeleteAppointmentUseCase @Inject constructor(
    private val repository: AppointmentRepository
) {
    suspend fun deleteAppointment(appointment: AppointmentInfo, uuid: String): Result<Boolean> {
        return repository.deleteAppointment(appointment, uuid)
    }
}