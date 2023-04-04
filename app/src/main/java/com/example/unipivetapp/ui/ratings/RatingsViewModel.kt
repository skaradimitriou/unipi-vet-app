package com.example.unipivetapp.ui.ratings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unipivetapp.ui.ratings.navigator.RatingsLandingMode

class RatingsViewModel : ViewModel() {

    val navigatorState: LiveData<RatingsLandingMode?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<RatingsLandingMode?>()

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: RatingsLandingMode?) {
        _navigatorState.postValue(action)
    }
}