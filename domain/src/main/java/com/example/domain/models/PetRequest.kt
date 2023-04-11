package com.example.domain.models

import android.graphics.Bitmap

data class PetRequest(
    val nickname: String? = null,
    val image: Bitmap? = null,
    val type: PetCategory
)
