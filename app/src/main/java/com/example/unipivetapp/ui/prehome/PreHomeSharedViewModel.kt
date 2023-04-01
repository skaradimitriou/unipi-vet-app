package com.example.unipivetapp.ui.prehome

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserData
import com.example.domain.usecases.profile.SetUserProfileDataUseCase
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PreHomeSharedViewModel @Inject constructor(
    app: Application,
    private val authenticator: Authenticator,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: SetUserProfileDataUseCase
) : BaseViewModel(app) {

    private val app = getApplication<Application>()

    val userDataSaved: LiveData<Boolean>
        get() = _userDataSaved

    private val _userDataSaved = MutableLiveData<Boolean>()

    private val _user = UserData()
    private var _bitmap: Bitmap? = null

    fun setUsername(username: String) = _user.apply { this.username = username }

    fun setImage(bitmap: Bitmap) = apply { _bitmap = bitmap }

    fun setFirstName(fName: String) = _user.apply { this.firstName = fName }

    fun setLastName(lName: String) = _user.apply { this.lastName = lName }

    fun setTelephone(tel: String) = _user.apply { this.telephone = tel }

    fun saveUserData() {
        viewModelScope.launch(dispatcher) {
            val bitmap = _bitmap ?: run {
                BitmapFactory.decodeResource(app.resources, R.drawable.profile_placeholder)
            }
            val uuid = authenticator.getActiveUser()?.uid.toString()
            useCase.setProfileInfo(_user, bitmap, uuid)
            _userDataSaved.postValue(true)
        }
    }
}