package com.example.unipivetapp.ui.dashboard.appointments

import android.app.Application
import com.example.unipivetapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

}