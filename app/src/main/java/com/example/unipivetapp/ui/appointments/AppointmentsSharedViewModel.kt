package com.example.unipivetapp.ui.appointments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.Vet
import com.example.unipivetapp.base.BaseViewModel
import javax.inject.Inject

class AppointmentsSharedViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    val selectedVet: LiveData<Vet>
        get() = _selectedVet

    private val _selectedVet = MutableLiveData<Vet>()

    fun setSelectedVet(vet: Vet) {
        _selectedVet.postValue(vet)
    }
}