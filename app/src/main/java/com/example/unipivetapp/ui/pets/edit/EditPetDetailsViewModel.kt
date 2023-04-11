package com.example.unipivetapp.ui.pets.edit

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Result
import com.example.domain.models.pets.Pet
import com.example.domain.models.pets.PetCategory
import com.example.domain.models.pets.UpdatePetRequest
import com.example.domain.usecases.pets.UpdatePetUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.ui.pets.add.uimodel.PetChip
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPetDetailsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val auth: Authenticator,
    private val useCase: UpdatePetUseCase
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

    var originalPet: Pet? = null

    private var _petImg: Bitmap? = null
    private var _petName: String? = null
    private var _petType: PetCategory = PetCategory.CAT

    fun setPetImage(image: Bitmap) = apply { _petImg = image }

    fun getPetImage() = _petImg

    fun setPetName(name: String) = apply { _petName = name }

    fun setPetType(type: PetCategory) = apply { _petType = type }

    fun getChips() {
        val list = listOf(
            PetChip(PetCategory.CAT),
            PetChip(PetCategory.DOG),
            PetChip(PetCategory.FISH),
            PetChip(PetCategory.BIRD),
            PetChip(PetCategory.RABBIT),
        )

        list.map { if (_petType == it.category) it.isSelected = true }
        _chips.postValue(list)
    }

    fun updatePet() {
        viewModelScope.launch(dispatcher) {
            val uuid = auth.getActiveUser()?.uid.toString()
            val petRequest = UpdatePetRequest(_petName, _petImg, _petType)
            val result = useCase.updatePet(originalPet, petRequest, uuid)
            _petAdded.postValue(result)
        }
    }

    fun validateInput(input: String) {
        setPetName(input)
        _btnState.postValue(input.isNotEmpty())
    }
}