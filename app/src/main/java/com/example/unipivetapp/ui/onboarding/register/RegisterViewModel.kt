package com.example.unipivetapp.ui.onboarding.register

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.StoreUserDataUseCase
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
class RegisterViewModel @Inject constructor(
    app: Application,
    private val authenticator: Authenticator,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: StoreUserDataUseCase
) : BaseViewModel(app) {

    /**
     * LiveData that holds the Result of the registration FirebaseAuth transaction.
     */

    val registrationResult: LiveData<Result<FirebaseUser>>
        get() = _registrationResult

    private val _registrationResult = MutableLiveData<Result<FirebaseUser>>()

    fun validateData(email: String, pass: String, confirmPass: String) {
        _registrationResult.postValue(Result.Loading())
        viewModelScope.launch(dispatcher) {
            val isPassValid = pass == confirmPass && pass.isNotEmpty() && confirmPass.isNotEmpty()
            if (email.isNotEmpty() && isPassValid) {
                val result = authenticator.register(email, pass)
                _registrationResult.postValue(result)

                if (result is Result.Success) {
                    useCase.setProfileInfo(email, authenticator.getActiveUser()?.uid.toString())
                }
            } else {
                _registrationResult.postValue(Result.Failure(GENERIC_ERROR))
            }
        }
    }
}