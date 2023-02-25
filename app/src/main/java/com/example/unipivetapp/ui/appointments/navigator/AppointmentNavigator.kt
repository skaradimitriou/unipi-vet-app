package com.example.unipivetapp.ui.appointments.navigator

interface AppointmentNavigator {
    fun navigateTo(screenKey: AppointmentAction)
    fun goBack()
}