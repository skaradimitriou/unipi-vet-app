package com.example.unipivetapp.ui.dashboard.home

import android.app.Application
import com.example.unipivetapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

}