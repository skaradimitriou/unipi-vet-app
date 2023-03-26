package com.example.unipivetapp.ui.appointmentdetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.AppointmentInfo
import com.example.domain.models.Result
import com.example.domain.usecases.appointments.DeleteAppointmentUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentDetailsViewModel @Inject constructor(
    app: Application,
    private val auth: Authenticator,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: DeleteAppointmentUseCase
) : BaseViewModel(app) {

    val appointmentDeleted: LiveData<Result<Boolean>>
        get() = _appointmentDeleted

    private val _appointmentDeleted = MutableLiveData<Result<Boolean>>()

    var appointment: AppointmentInfo? = null

    fun cancelAppointment() {
        viewModelScope.launch(dispatcher) {
            val uuid = auth.getActiveUser()?.uid.toString()
            appointment?.let {
                val result = useCase.deleteAppointment(it, uuid)
                _appointmentDeleted.postValue(result)
            }
        }
    }
}