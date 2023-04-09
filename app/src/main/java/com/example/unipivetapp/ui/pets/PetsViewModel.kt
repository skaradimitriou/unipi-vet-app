package com.example.unipivetapp.ui.pets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Pet
import com.example.unipivetapp.ui.pets.navigator.PetAction

class PetsViewModel : ViewModel() {

    val navigatorState: LiveData<PetAction?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<PetAction?>()

    private var _selectedPet: Pet? = null

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: PetAction?) {
        _navigatorState.postValue(action)
    }

    fun setSelectedPet(pet: Pet) = apply { _selectedPet = pet }

    fun getSelectedPet() = _selectedPet
}