package com.example.domain.usecases.profile

import com.example.domain.repositories.ProfileRepository
import javax.inject.Inject

class GetProfileInfoUseCase @Inject constructor(
    private val repo: ProfileRepository
) {
    suspend fun getProfileInfo(uuid: String) = repo.getProfileInfo(uuid)
}