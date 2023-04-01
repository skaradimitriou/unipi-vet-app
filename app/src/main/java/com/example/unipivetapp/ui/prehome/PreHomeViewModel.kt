package com.example.unipivetapp.ui.prehome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unipivetapp.ui.prehome.navigator.PreHomeAction

class PreHomeViewModel : ViewModel() {

    val navigatorState: LiveData<PreHomeAction?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<PreHomeAction?>()

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: PreHomeAction?) {
        _navigatorState.postValue(action)
    }
}