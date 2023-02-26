package com.example.unipivetapp.ui.appointments.calendar

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.unipivetapp.base.BaseViewModel
import com.example.unipivetapp.di.IoDispatcher
import com.example.unipivetapp.ui.appointments.calendar.uimodel.Day
import com.example.unipivetapp.ui.appointments.calendar.uimodel.TimeSlot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    app: Application,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    val days: LiveData<List<Day>>
        get() = _days

    private val _days = MutableLiveData<List<Day>>()

    val times: LiveData<List<TimeSlot>>
        get() = _times

    private val _times = MutableLiveData<List<TimeSlot>>()

    private var selectedDay: Day? = null
    private var selectedTimeslot: TimeSlot? = null

    init {
        getDays()
        getAllTimeIntervals()
    }

    fun getDays() {
        viewModelScope.launch(dispatcher) {
            val dates = getAllDateOfMonth()
            _days.postValue(dates)
        }
    }

    private fun getAllDateOfMonth(): List<Day> {
        val yearMonth = YearMonth.of(LocalDateTime.now().year, LocalDateTime.now().month)
        val firstDayOfTheMonth = yearMonth.atDay(1)
        val datesOfThisMonth = mutableListOf<Day>()
        for (daysNo in 0 until yearMonth.lengthOfMonth()) {
            val value = firstDayOfTheMonth.plusDays(daysNo.toLong())
            val isCurrentDay = LocalDateTime.now().dayOfMonth == value.dayOfMonth
            val day = Day(value, isCurrentDay)
            datesOfThisMonth.add(day)
        }
        return datesOfThisMonth
    }

    private fun getAllTimeIntervals() {
        val timeIntervals = listOf(
            TimeSlot("11:00", isSelected = true),
            TimeSlot("11:30"),
            TimeSlot("12:00"),
            TimeSlot("12:30"),
            TimeSlot("13:00"),
            TimeSlot("13:30"),
            TimeSlot("14:00"),
            TimeSlot("14:30"),
            TimeSlot("16:30"),
            TimeSlot("17:00"),
            TimeSlot("17:30"),
            TimeSlot("18:00"),
            TimeSlot("18:30"),
            TimeSlot("19:00"),
            TimeSlot("19:30"),
            TimeSlot("20:00"),
            TimeSlot("20:30")
        )
        _times.postValue(timeIntervals)
    }

    fun getSelectedDay() {

    }

    fun getSelectedSlot() {

    }
}