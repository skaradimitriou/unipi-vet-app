package com.example.unipivetapp.ui.dashboard.profile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserInfo
import com.example.domain.usecases.profile.GetProfileInfoUseCase
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileOption
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileOptionType
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileScreenUiModel
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

    val userInfo: LiveData<ProfileScreenUiModel>
        get() = _userInfo

    private val _userInfo = MutableLiveData<ProfileScreenUiModel>()

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

    private fun UserInfo.toUiData() = ProfileScreenUiModel(
        userImg = userImg,
        username = username,
        items = listOf(
            ProfileOption(getString(R.string.email), email),
            ProfileOption(getString(R.string.telephone), telephone),
            ProfileOption(getString(R.string.first_name), firstName),
            ProfileOption(getString(R.string.last_name), lastName),
            ProfileOption(
                getString(R.string.logout),
                getString(R.string.exit_the_app),
                ProfileOptionType.LOGOUT
            ),
        )
    )

    fun logoutUser() {
        val result = authenticator.logout()
        _userLoggedOut.postValue(result)
    }
}