package com.example.unipivetapp.ui.onboarding.intro

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    app: Application,
    private val auth: Authenticator,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    val userIsLoggedIn: LiveData<Boolean>
        get() = _userIsLoggedIn

    private val _userIsLoggedIn = MutableLiveData<Boolean>()

    fun getCurrentUser() {
        viewModelScope.launch(dispatcher) {
            auth.getActiveUser()?.let { user ->
                _userIsLoggedIn.postValue(true)
            } ?: kotlin.run {
                _userIsLoggedIn.postValue(false)
            }
        }
    }
}