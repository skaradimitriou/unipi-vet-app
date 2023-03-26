package com.example.unipivetapp.ui.dashboard.profile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UiModel
import com.example.domain.models.UserInfo
import com.example.domain.usecases.profile.GetProfileInfoUseCase
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileHeader
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileLogoutOption
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileOption
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    app: Application,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val authenticator: Authenticator,
    private val useCase: GetProfileInfoUseCase
) : BaseViewModel(app) {

    val userInfo: LiveData<List<UiModel>>
        get() = _userInfo

    private val _userInfo = MutableLiveData<List<UiModel>>()

    val userLoggedOut: LiveData<Boolean>
        get() = _userLoggedOut

    private val _userLoggedOut = MutableLiveData<Boolean>()

    fun getProfileInfo() {
        viewModelScope.launch(dispatcher) {
            val uid = authenticator.getActiveUser()?.uid.toString()
            val result = useCase.getProfileInfo(uid)
            _userInfo.postValue(result.toUiData())
        }
    }

    private fun UserInfo.toUiData() = listOf(
        ProfileHeader(userImg, username),
        ProfileOption(getString(R.string.email), email),
        ProfileOption(getString(R.string.telephone), telephone),
        ProfileOption(getString(R.string.first_name), firstName),
        ProfileOption(getString(R.string.last_name), lastName),
        ProfileLogoutOption(getString(R.string.logout))
    )

    fun logoutUser() {
        val result = authenticator.logout()
        _userLoggedOut.postValue(result)
    }
}