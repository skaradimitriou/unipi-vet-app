package com.example.domain.usecases.profile

import com.example.domain.models.UpdateUserInfo
import com.example.domain.repositories.ProfileRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val repo: ProfileRepository
) {
    suspend fun updateProfileInfo(data: UpdateUserInfo, uuid: String) {
        repo.updateProfileInfo(data, uuid)
    }
}