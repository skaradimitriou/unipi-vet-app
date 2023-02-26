package com.example.domain.usecases

import com.example.domain.models.AppointmentInfo
import com.example.domain.repositories.AppointmentRepository
import javax.inject.Inject

class GetMyAppointmentsUseCase @Inject constructor(
    private val repository: AppointmentRepository
) {
    suspend fun getMyAppointments(uuid: String): com.example.domain.models.Result<List<AppointmentInfo>> {
        return repository.getMyAppointments(uuid)
    }
}