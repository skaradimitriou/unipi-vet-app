package com.example.unipivetapp.ui.dashboard.pets

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.pets.Pet
import com.example.domain.usecases.pets.GetMyPetsUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val auth: Authenticator,
    private val useCase: GetMyPetsUseCase
) : BaseViewModel(app) {

    val pets: LiveData<List<Pet>>
        get() = _pets

    private val _pets = MutableLiveData<List<Pet>>()

    fun getMyPets() {
        viewModelScope.launch(dispatcher) {
            val user = auth.getActiveUser()?.uid.toString()
            val result = useCase.getMyPets(user)
            _pets.postValue(result)
        }
    }
}