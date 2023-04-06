package com.example.unipivetapp.ui.dashboard.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.combiners.GetDashboardDataCombiner
import com.example.domain.models.DashboardModel
import com.example.domain.models.Result
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.util.auth.Authenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val authenticator: Authenticator,
    private val useCase: GetDashboardDataCombiner,
) : BaseViewModel(app) {

    val dashboardData: LiveData<Result<DashboardModel>>
        get() = _dashboardData

    private val _dashboardData = MutableLiveData<Result<DashboardModel>>()

    fun getDashboardData() {
        _dashboardData.postValue(Result.Loading())
        viewModelScope.launch(dispatcher) {
            val uuid = authenticator.getActiveUser()?.uid ?: ""
            val data = useCase.getDashboardData(uuid)
            _dashboardData.postValue(data)
        }
    }
}