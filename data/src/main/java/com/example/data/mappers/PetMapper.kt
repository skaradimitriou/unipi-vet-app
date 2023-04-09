package com.example.data.mappers

import com.example.data.models.PetResponseDto
import com.example.data.util.toNotNull
import com.example.domain.models.Pet
import com.example.domain.models.PetCategory

object PetMapper : Mapper<PetResponseDto?, List<Pet>> {

    override fun toDomainModel(dto: PetResponseDto?): List<Pet> {
        return dto?.pets?.map {
            Pet(
                nickname = it.nickname.toNotNull(),
                image = it.image.toNotNull(),
                type = PetCategory.valueOf(it.type?.name.toNotNull())
            )
        } ?: listOf()
    }
}