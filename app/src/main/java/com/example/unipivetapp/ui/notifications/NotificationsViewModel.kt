package com.example.unipivetapp.ui.notifications

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Notification
import com.example.domain.usecases.notifications.GetAllNotificationsUseCase
import com.example.domain.usecases.notifications.ReadAllNotificationsUseCase
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val auth: Authenticator,
    private val getNotificationsUseCase: GetAllNotificationsUseCase,
    private val readAllNotificationsUseCase: ReadAllNotificationsUseCase,
) : BaseViewModel(app) {

    val notifications: LiveData<List<Notification>>
        get() = _notifications

    private val _notifications = MutableLiveData<List<Notification>>()

    fun getAllNotifications() {
        viewModelScope.launch(dispatcher) {
            val uuid = auth.getActiveUser()?.uid.toString()
            val result = getNotificationsUseCase.getMyNotifications(uuid)
            _notifications.postValue(result)
        }
    }

    fun readAllNotifications() {
        viewModelScope.launch(dispatcher) {
            val uuid = auth.getActiveUser()?.uid.toString()
            readAllNotificationsUseCase.readAllNotifications(uuid)
        }
    }
}