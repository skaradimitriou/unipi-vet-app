package com.example.unipivetapp.ui.pets.edit

import android.app.Application
import com.example.unipivetapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditPetDetailsViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {


}