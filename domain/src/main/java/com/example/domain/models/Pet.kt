package com.example.domain.models

data class Pet(
    val nickname: String,
    val image: String,
    val type: PetCategory,
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is Pet -> nickname == obj.nickname && image == obj.image && type == obj.type
        else -> false
    }
}

enum class PetCategory {
    CAT, DOG, FISH, BIRD, RABBIT
}