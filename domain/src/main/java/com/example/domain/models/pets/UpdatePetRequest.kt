package com.example.domain.models.pets

import android.graphics.Bitmap

data class UpdatePetRequest(
    val nickname: String? = null,
    val image: Bitmap? = null,
    val type: PetCategory
)
