package com.example.domain.models.pets

import android.os.Parcelable
import com.example.domain.models.UiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    var nickname: String,
    var image: String,
    var type: PetCategory,
) : UiModel, Parcelable {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is Pet -> nickname == obj.nickname && image == obj.image && type == obj.type
        else -> false
    }
}

enum class PetCategory {
    CAT, DOG, FISH, BIRD, RABBIT
}