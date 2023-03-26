package com.example.domain.usecases.profile

import com.example.domain.repositories.ProfileRepository
import javax.inject.Inject

class StoreUserDataUseCase @Inject constructor(
    private val repo: ProfileRepository
) {
    suspend fun setProfileInfo(email: String, uuid: String) = repo.setProfileInfo(email, uuid)
}