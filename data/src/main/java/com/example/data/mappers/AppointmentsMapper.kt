package com.example.data.mappers

import com.example.data.mappers.VetMapper.toDomainModel
import com.example.data.models.AppointmentDto
import com.example.data.util.toNotNull
import com.example.domain.models.AppointmentInfo

object AppointmentsMapper : Mapper<List<AppointmentDto?>?, List<AppointmentInfo>> {

    override fun toDomainModel(dto: List<AppointmentDto?>?): List<AppointmentInfo> = dto?.map {
        AppointmentInfo(
            vet = it?.vet.toDomainModel(),
            appointmentDateAndTime = it?.appointmentDateAndTime.toNotNull(),
            uuid = it?.uuid.toNotNull(),
            firestoreId = it?.firestoreId.toNotNull()
        )
    } ?: listOf()
}