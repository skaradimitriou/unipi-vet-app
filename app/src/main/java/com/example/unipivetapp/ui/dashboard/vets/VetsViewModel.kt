package com.example.unipivetapp.ui.dashboard.vets

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Rating
import com.example.domain.models.Vet
import com.example.domain.usecases.ratings.GetAllRatingsUseCase
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
    private val ratingsUseCase: GetAllRatingsUseCase,
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
            val ratings = ratingsUseCase.getAllRatings()
            val result = applyRatings(data, ratings)
            _vets.postValue(result)
        }
    }

    fun getAllVets() {
        viewModelScope.launch(dispatcher) {
            val data = useCase.getAllVets()
            val ratings = ratingsUseCase.getAllRatings()
            val result = applyRatings(data, ratings)
            _vets.postValue(result)
        }
    }

    private fun applyRatings(vetList: List<Vet>, ratings: List<Rating>): List<Vet> {
        val combinedData = vetList.apply {
            map { vet ->
                val list = ratings.filter { it.doctorId == vet.id }
                val vetRatings = if (list.isEmpty()) 0.0 else list.map { it.value }.average()
                vet.rating = vetRatings
            }
        }

        return combinedData
    }
}