package com.example.unipivetapp.ui.dashboard.vets

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Vet
import com.example.domain.usecases.vets.GetAllVetsUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VetsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: GetAllVetsUseCase
) : BaseViewModel(app) {

    val vets: LiveData<List<Vet>>
        get() = _vets

    private val _vets = MutableLiveData<List<Vet>>()

    fun getAllVets() {
        viewModelScope.launch(dispatcher) {
            val data = useCase.getAllVets()
            _vets.postValue(data)
        }
    }
}