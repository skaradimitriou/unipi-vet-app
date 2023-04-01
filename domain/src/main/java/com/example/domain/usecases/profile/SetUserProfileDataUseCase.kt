package com.example.domain.usecases.profile

import android.graphics.Bitmap
import com.example.domain.models.UserData
import com.example.domain.repositories.ProfileRepository
import javax.inject.Inject

class SetUserProfileDataUseCase @Inject constructor(
    private val repo: ProfileRepository
) {
    suspend fun setProfileInfo(userData: UserData, userImg: Bitmap, uuid: String) {
        return repo.setProfileInfo(userData, userImg, uuid)
    }
}