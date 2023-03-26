package com.example.unipivetapp.ui.appointments.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Appointment
import com.example.domain.models.Result
import com.example.domain.models.Vet
import com.example.domain.usecases.appointments.StoreAppointmentUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: StoreAppointmentUseCase,
    private val auth: Authenticator
) : BaseViewModel(app) {

    val appointmentSaved: LiveData<Result<Boolean>>
        get() = _appointmentSaved

    private val _appointmentSaved = MutableLiveData<Result<Boolean>>()

    fun saveAppointment(appointment: Appointment, vet: Vet) {
        viewModelScope.launch(dispatcher) {
            val result = useCase.saveAppointment(appointment, vet, auth.getActiveUser()?.uid.toString())
            _appointmentSaved.postValue(result)
        }
    }
}