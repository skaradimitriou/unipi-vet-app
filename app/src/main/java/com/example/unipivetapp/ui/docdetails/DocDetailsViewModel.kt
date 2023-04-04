package com.example.unipivetapp.ui.docdetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.UiModel
import com.example.domain.models.Vet
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.ui.docdetails.uimodel.DocAppointment
import com.example.unipivetapp.ui.docdetails.uimodel.DocHeader
import com.example.unipivetapp.ui.docdetails.uimodel.DocReview
import javax.inject.Inject

class DocDetailsViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    val vetInfo: LiveData<List<UiModel>>
        get() = _vetInfo

    private val _vetInfo = MutableLiveData<List<UiModel>>()

    private var selectedVet: Vet? = null

    fun displayVetDetails(vet: Vet) {
        selectedVet = vet
        _vetInfo.postValue(vet.toUiModel())
    }

    fun getVetInfo() = selectedVet

    private fun Vet.toUiModel() = listOf(
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
