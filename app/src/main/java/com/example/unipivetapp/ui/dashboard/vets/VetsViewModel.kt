package com.example.unipivetapp.ui.dashboard.vets

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Vet
import com.example.domain.usecases.vets.GetAllVetsUseCase
import com.example.domain.usecases.vets.SearchVetByNameUseCase
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
    private val useCase: GetAllVetsUseCase,
    private val queryUseCase: SearchVetByNameUseCase
) : BaseViewModel(app) {

    val vets: LiveData<List<Vet>>
        get() = _vets

    private val _vets = MutableLiveData<List<Vet>>()

    fun handleQuery(query: String) {
        if (query.isEmpty()) {
            getAllVets()
        } else {
            searchVetByName(query)
        }
    }

    private fun searchVetByName(name: String) {
        viewModelScope.launch(dispatcher) {
            val data = queryUseCase.searchByName(name)
            _vets.postValue(data)
        }
    }

    fun getAllVets() {
        viewModelScope.launch(dispatcher) {
            val data = useCase.getAllVets()
            _vets.postValue(data)
        }
    }
}