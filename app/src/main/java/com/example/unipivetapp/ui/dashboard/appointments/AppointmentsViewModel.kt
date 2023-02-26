package com.example.unipivetapp.ui.dashboard.appointments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.AppointmentInfo
import com.example.domain.models.Result
import com.example.domain.usecases.GetMyAppointmentsUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    app: Application,
    private val auth: Authenticator,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: GetMyAppointmentsUseCase
) : BaseViewModel(app) {

    val appointments: LiveData<Result<List<AppointmentInfo>>>
        get() = _appointments

    private val _appointments = MutableLiveData<Result<List<AppointmentInfo>>>()

    fun getMyAppointments() {
        viewModelScope.launch(dispatcher) {
            val uuid = auth.getActiveUser()?.uid.toString()
            val result = useCase.getMyAppointments(uuid)
            _appointments.postValue(result)
        }
    }
}