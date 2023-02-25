package com.example.unipivetapp.ui.appointments.overview

import android.app.Application
import com.example.unipivetapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    init {
        //
    }
}