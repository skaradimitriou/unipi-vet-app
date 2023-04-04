package com.example.unipivetapp.ui.ratings.all

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Rating
import com.example.domain.usecases.ratings.GetAllRatingsUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllRatingsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: GetAllRatingsUseCase
) : BaseViewModel(app) {

    val ratings: LiveData<List<Rating>>
        get() = _ratings

    private val _ratings = MutableLiveData<List<Rating>>()

    fun getAllRatings(docId: Int) {
        viewModelScope.launch(dispatcher) {
            val data = useCase.getAllRatings(docId)
            _ratings.postValue(data)
        }
    }
}