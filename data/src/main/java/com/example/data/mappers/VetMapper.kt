package com.example.data.mappers

import com.example.data.models.VetDto
import com.example.data.util.toNotNull
import com.example.domain.models.Vet

object VetMapper : Mapper<List<VetDto>?, List<Vet>> {

    override fun toDomainModel(dto: List<VetDto>?): List<Vet> {
        return dto?.map { it.toDomainModel() } ?: listOf()
    }

    fun VetDto?.toDomainModel() = Vet(
        category = this?.category.toNotNull(),
        experience = this?.experience.toNotNull(),
        firstName = this?.firstName.toNotNull(),
        lastName = this?.lastName.toNotNull(),
        fullName = this?.fullName.toNotNull(),
        image = this?.image.toNotNull(),
        mobileNo = this?.mobileNo.toNotNull(),
        rating = this?.rating.toNotNull(),
    )
}