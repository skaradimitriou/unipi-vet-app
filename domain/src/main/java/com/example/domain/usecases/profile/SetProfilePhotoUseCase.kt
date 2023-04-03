package com.example.domain.usecases.profile

import android.graphics.Bitmap
import com.example.domain.repositories.ProfileRepository
import javax.inject.Inject

class SetProfilePhotoUseCase @Inject constructor(
    private val repo: ProfileRepository
) {
    suspend fun setProfilePhoto(bitmap: Bitmap, uuid: String) = repo.setProfilePhoto(bitmap, uuid)
}