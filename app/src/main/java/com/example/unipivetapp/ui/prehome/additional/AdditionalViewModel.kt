package com.example.unipivetapp.ui.prehome.additional

import android.app.Application
import com.example.unipivetapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdditionalViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

}