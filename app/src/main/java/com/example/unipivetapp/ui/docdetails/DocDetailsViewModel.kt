package com.example.unipivetapp.ui.docdetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UiModel
import com.example.domain.models.Vet
import com.example.domain.usecases.ratings.GetAllRatingsByIdUseCase
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.ui.docdetails.uimodel.DocAppointment
import com.example.unipivetapp.ui.docdetails.uimodel.DocHeader
import com.example.unipivetapp.ui.docdetails.uimodel.DocReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocDetailsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val ratingUseCase: GetAllRatingsByIdUseCase
) : BaseViewModel(app) {

    val vetInfo: LiveData<List<UiModel>>
        get() = _vetInfo

    private val _vetInfo = MutableLiveData<List<UiModel>>()

    private var selectedVet: Vet? = null

    fun displayVetDetails(vet: Vet) {
        selectedVet = vet
        viewModelScope.launch(dispatcher) {
            val result = ratingUseCase.getAllRatings(vet.id).map { it.value }.average()
            _vetInfo.postValue(vet.toUiModel(rating = result))
        }
    }

    fun getVetInfo() = selectedVet

    private fun Vet.toUiModel(rating: Double) = listOf(
        DocHeader(
            image = image,
            fullName = fullName,
            category = category,
            experience = experience,
            rating = rating
        ),
        DocReview(
            title = getString(R.string.vet_reviews),
            description = String.format(getString(R.string.vet_reviews_placeholder), fullName),
            docId = id
        ),
        DocAppointment(
            title = getString(R.string.vet_appointment),
            description = getString(R.string.vet_appointment_desc),
            btnText = getString(R.string.vet_appointment_btn)
        )
    )
}
