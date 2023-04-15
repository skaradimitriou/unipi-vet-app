package com.example.unipivetapp.ui.ratings.add

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Rating
import com.example.domain.usecases.ratings.SetRatingForVetUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRatingViewModel @Inject constructor(
    app: Application,
    private val auth: Authenticator,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: SetRatingForVetUseCase
) : BaseViewModel(app) {

    val ratingSaved: LiveData<Boolean>
        get() = _ratingSaved

    private val _ratingSaved = MutableLiveData<Boolean>()

    val validRating: LiveData<Boolean>
        get() = _validRating

    private val _validRating = MutableLiveData(false)

    private var docId: Int = 0

    fun setDoctorId(docId: Int) = apply { this.docId = docId }

    fun validateInput(title: String) {
        _validRating.postValue(title.isNotEmpty())
    }

    fun setRating(title: String, description: String, rating: Double) {
        viewModelScope.launch(dispatcher) {
            val model = Rating(title, description, rating, docId)
            val uuid = auth.getActiveUser()?.uid.toString()
            useCase.setRating(model, uuid)
            _ratingSaved.postValue(true)
        }
    }
}