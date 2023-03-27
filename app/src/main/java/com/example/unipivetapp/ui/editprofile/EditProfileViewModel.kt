package com.example.unipivetapp.ui.editprofile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UiModel
import com.example.domain.models.UpdateUserInfo
import com.example.domain.models.UserInfo
import com.example.domain.usecases.profile.GetProfileInfoUseCase
import com.example.domain.usecases.profile.UpdateProfileUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileHeader
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    app: Application,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val authenticator: Authenticator,
    private val getProfileUseCase: GetProfileInfoUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase
) : BaseViewModel(app) {

    val userInfo: LiveData<List<UiModel>>
        get() = _userInfo

    private val _userInfo = MutableLiveData<List<UiModel>>()

    val profileUpdated: LiveData<Boolean>
        get() = _profileUpdated

    private val _profileUpdated = MutableLiveData<Boolean>()

    fun getProfileInfo() {
        viewModelScope.launch(dispatcher) {
            val uid = authenticator.getActiveUser()?.uid.toString()
            val result = getProfileUseCase.getProfileInfo(uid)
            _userInfo.postValue(result.toUiData())
        }
    }

    private fun UserInfo.toUiData() = listOf(
        ProfileHeader(userImg, username),
        this
    )

    fun saveUserData(data: UpdateUserInfo) {
        viewModelScope.launch(dispatcher) {
            val uid = authenticator.getActiveUser()?.uid.toString()
            updateProfileUseCase.updateProfileInfo(data, uid)
            _profileUpdated.postValue(true)
        }
    }
}