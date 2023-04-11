package com.example.unipivetapp.ui.pets.add

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Result
import com.example.domain.models.pets.PetCategory
import com.example.domain.models.pets.PetRequest
import com.example.domain.usecases.pets.AddPetUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.ui.pets.add.uimodel.PetChip
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    app: Application,
    private val auth: Authenticator,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: AddPetUseCase
) : BaseViewModel(app) {

    val petAdded: LiveData<Result<Boolean>>
        get() = _petAdded

    private val _petAdded = MutableLiveData<Result<Boolean>>()

    val chips: LiveData<List<PetChip>>
        get() = _chips

    private val _chips = MutableLiveData<List<PetChip>>()

    val btnState: LiveData<Boolean>
        get() = _btnState

    private val _btnState = MutableLiveData(false)

    private var _petImg: Bitmap? = null
    private var _petName: String? = null
    private var _petType: PetCategory = PetCategory.CAT

    fun setPetImage(image: Bitmap) = apply { _petImg = image }

    fun setPetName(name: String) = apply { _petName = name }

    fun setPetType(type: PetCategory) = apply { _petType = type }

    fun getChips() {
        val list = listOf(
            PetChip(PetCategory.CAT, isSelected = true),
            PetChip(PetCategory.DOG),
            PetChip(PetCategory.FISH),
            PetChip(PetCategory.BIRD),
            PetChip(PetCategory.RABBIT),
        )
        _chips.postValue(list)
    }

    fun addPet() {
        viewModelScope.launch(dispatcher) {
            val uuid = auth.getActiveUser()?.uid.toString()
            val petRequest = PetRequest(_petName, _petImg, _petType)
            val result = useCase.addNewPet(petRequest, uuid)
            _petAdded.postValue(result)
        }
    }

    fun validateInput(input: String) {
        setPetName(input)
        _btnState.postValue(input.isNotEmpty())
    }
}