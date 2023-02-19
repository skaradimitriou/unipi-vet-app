package com.example.unipivetapp.ui.onboarding.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Result
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.GENERIC_ERROR
import com.example.unipivetapp.util.auth.Authenticator
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticator: Authenticator,
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    /**
     * LiveData that holds the Result of the login FirebaseAuth transaction.
     */

    val loginResult: LiveData<Result<FirebaseUser>>
        get() = _loginResult

    private val _loginResult = MutableLiveData<Result<FirebaseUser>>()

    fun login(email: String, pass: String) {
        viewModelScope.launch(dispatcher) {
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                val result = authenticator.login(email, pass)
                _loginResult.postValue(result)
            } else {
                _loginResult.postValue(Result.Failure(GENERIC_ERROR))
            }
        }
    }
}