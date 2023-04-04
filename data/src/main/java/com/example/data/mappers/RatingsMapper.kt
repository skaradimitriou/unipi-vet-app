package com.example.data.mappers

import com.example.data.models.RatingDto
import com.example.data.util.toNotNull
import com.example.domain.models.Rating

object RatingsMapper : Mapper<List<RatingDto>?, List<Rating>> {

    override fun toDomainModel(dto: List<RatingDto>?): List<Rating> {
        return dto?.map {
            Rating(
                title = it.title.toNotNull(),
                description = it.description.toNotNull(),
                value = it.value.toNotNull()
            )
        } ?: listOf()
    }
}