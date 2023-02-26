package com.example.unipivetapp.ui.appointments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.Appointment
import com.example.domain.models.Vet
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.ui.appointments.calendar.uimodel.Day
import com.example.unipivetapp.ui.appointments.calendar.uimodel.TimeSlot
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AppointmentsSharedViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    val selectedVet: LiveData<Vet>
        get() = _selectedVet

    private val _selectedVet = MutableLiveData<Vet>()

    val appointment: LiveData<Appointment>
        get() = _appointment

    private val _appointment = MutableLiveData<Appointment>()

    private var selectedDay: Day? = null
    private var selectedTimeSlot: TimeSlot? = null

    fun setSelectedVet(vet: Vet) {
        _selectedVet.postValue(vet)
    }

    fun setSelectedDayOfMonth(day: Day?) {
        selectedDay = day
    }

    fun setSelectedTimeSlot(slot: TimeSlot?) {
        selectedTimeSlot = slot
    }

    fun getAppointmentData() {
        val appointment = Appointment(
            date = selectedDay?.value ?: LocalDate.now(),
            timeslot = selectedTimeSlot?.value.toString()
        )
        _appointment.postValue(appointment)
    }
}