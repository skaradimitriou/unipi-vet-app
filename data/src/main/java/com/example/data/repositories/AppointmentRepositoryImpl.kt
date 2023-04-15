package com.example.data.repositories

import android.app.Application
import com.example.data.R
import com.example.data.mappers.AppointmentsMapper
import com.example.data.models.AppointmentDto
import com.example.data.util.APPOINTMENTS_DB_PATH
import com.example.data.util.UUID
import com.example.data.util.toListOf
import com.example.domain.models.*
import com.example.domain.repositories.AppointmentRepository
import com.example.domain.repositories.NotificationsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val app: Application,
    private val firestore: FirebaseFirestore,
    private val notificationsRepo: NotificationsRepository
) : AppointmentRepository {

    override suspend fun saveAppointment(
        appointment: Appointment,
        vet: Vet,
        userId: String
    ): Result<Boolean> {
        var result: Result<Boolean> = Result.Loading()
        firestore.collection(APPOINTMENTS_DB_PATH).document().apply {
            val data = AppointmentInfo(
                vet = vet,
                appointmentDateAndTime = appointment.toFirestoreFormat().toString(),
                uuid = userId,
                firestoreId = id //auto-generated id for Firestore document.
            )

            set(data)
                .addOnSuccessListener {
                    result = Result.Success(true)

                    notificationsRepo.setNewNotification(
                        notification = Notification(
                            title = app.getString(R.string.new_appintment_notif_title),
                            description = app.getString(
                                R.string.new_appintment_notif_desc,
                                data.getDisplayedDate(),
                                data.vet.fullName
                            )
                        ),
                        uuid = userId
                    )
                }
                .addOnFailureListener {
                    result = Result.Failure(error = it.message.toString())
                }
                .await()
        }

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

    override suspend fun deleteAppointment(
        appointment: AppointmentInfo,
        userId: String
    ): Result<Boolean> {
        var result: Result<Boolean> = Result.Loading()

        firestore.collection(APPOINTMENTS_DB_PATH)
            .document(appointment.firestoreId)
            .delete()
            .addOnSuccessListener {
                result = Result.Success(true)

                notificationsRepo.setNewNotification(
                    notification = Notification(
                        title = app.getString(R.string.cancel_appintment_notif_title),
                        description = app.getString(
                            R.string.cancel_appintment_notif_desc,
                            appointment.getDisplayedDate(),
                            appointment.vet.fullName
                        )
                    ),
                    uuid = userId
                )
            }
            .addOnFailureListener {
                result = Result.Failure(error = it.message.toString())
            }
            .await()

        return result
    }
}