package com.example.data.models

data class PetResponseDto(
    val pets: List<PetDto>? = null
)

data class PetDto(
    val nickname: String? = null,
    val image: String? = null,
    val type: PetCategoryDto? = null,
)

enum class PetCategoryDto {
    CAT, DOG, FISH, BIRD, RABBIT
}