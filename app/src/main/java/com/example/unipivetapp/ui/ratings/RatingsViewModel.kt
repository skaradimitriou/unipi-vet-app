package com.example.unipivetapp.ui.ratings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unipivetapp.ui.ratings.navigator.RatingsAction

class RatingsViewModel : ViewModel() {

    val navigatorState: LiveData<RatingsAction?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<RatingsAction?>()

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: RatingsAction?) {
        _navigatorState.postValue(action)
    }
}