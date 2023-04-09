package com.example.unipivetapp.ui.pets.add

import android.app.Application
import com.example.unipivetapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

}