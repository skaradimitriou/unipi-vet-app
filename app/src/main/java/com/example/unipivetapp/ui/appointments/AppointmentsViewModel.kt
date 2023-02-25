package com.example.unipivetapp.ui.appointments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unipivetapp.ui.appointments.navigator.AppointmentAction

class AppointmentsViewModel : ViewModel() {

    val navigatorState: LiveData<AppointmentAction?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<AppointmentAction?>()

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: AppointmentAction?) {
        _navigatorState.postValue(action)
    }
}