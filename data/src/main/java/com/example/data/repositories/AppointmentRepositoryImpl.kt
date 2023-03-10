package com.example.data.repositories

import com.example.data.mappers.AppointmentsMapper
import com.example.data.models.AppointmentDto
import com.example.data.util.APPOINTMENTS_DB_PATH
import com.example.data.util.UUID
import com.example.data.util.toListOf
import com.example.domain.models.Appointment
import com.example.domain.models.AppointmentInfo
import com.example.domain.models.Result
import com.example.domain.models.Vet
import com.example.domain.repositories.AppointmentRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : AppointmentRepository {

    override suspend fun saveAppointment(
        appointment: Appointment,
        vet: Vet,
        userId: String
    ): Result<Boolean> {
        val data = AppointmentInfo(
            vet = vet,
            appointmentDateAndTime = appointment.toFirestoreFormat().toString(),
            uuid = userId
        )

        var result: Result<Boolean> = Result.Loading()
        firestore.collection(APPOINTMENTS_DB_PATH)
            .document()
            .set(data)
            .addOnSuccessListener {
                result = Result.Success(true)
            }
            .addOnFailureListener {
                result = Result.Failure(error = it.message.toString())
            }
            .await()

        return result
    }

    override suspend fun getMyAppointments(uuid: String): Result<List<AppointmentInfo>> {
        val result = firestore.collection(APPOINTMENTS_DB_PATH)
            .whereEqualTo(UUID, uuid)
            .get()
            .await()
            .toListOf<AppointmentDto>()

        return Result.Success(AppointmentsMapper.toDomainModel(result))
    }
}