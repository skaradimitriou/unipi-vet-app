package com.example.unipivetapp.ui.appointments.calendar

import android.app.Application
import com.example.unipivetapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    init {
        //
    }
}