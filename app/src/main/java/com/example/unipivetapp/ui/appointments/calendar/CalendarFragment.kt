package com.example.unipivetapp.ui.appointments.calendar

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentCalendarBinding
import com.example.unipivetapp.ui.appointments.AppointmentsSharedViewModel
import com.example.unipivetapp.ui.appointments.AppointmentsViewModel
import com.example.unipivetapp.ui.appointments.calendar.adapter.DaysAdapter
import com.example.unipivetapp.ui.appointments.calendar.adapter.TimeAdapter
import com.example.unipivetapp.ui.appointments.navigator.AppointmentAction
import com.example.unipivetapp.util.STANDARD_DELAY
import com.example.unipivetapp.util.ext.setMonthName
import com.example.unipivetapp.util.ext.setScreenTitle
import com.example.unipivetapp.util.ext.withDelay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel: CalendarViewModel by viewModels()
    private val sharedViewModel: AppointmentsSharedViewModel by activityViewModels()
    private val activityViewModel: AppointmentsViewModel by activityViewModels()

    private val daysAdapter = DaysAdapter { selectedDay ->
        sharedViewModel.setSelectedDayOfMonth(selectedDay)
    }

    private val timeAdapter = TimeAdapter { selectedSlot ->
        sharedViewModel.setSelectedTimeSlot(selectedSlot)
    }

    override fun init() {
        setScreenTitle(getString(R.string.appointment_title))

        binding.daysAdapter = daysAdapter
        binding.timesAdapter = timeAdapter
        binding.currentMonthTxtView.setMonthName()

        binding.continueBtn.setOnClickListener {
            activityViewModel.navigateToScreen(AppointmentAction.OVERVIEW)
        }
    }

    override fun startOps() {
        viewModel.days.observe(viewLifecycleOwner) { days ->
            daysAdapter.submitList(days)
            sharedViewModel.setSelectedDayOfMonth(days.find { it.isSelected })

            withDelay(STANDARD_DELAY) {
                val position = days.indexOfFirst { it.isSelected }
                binding.daysRecycler.smoothScrollToPosition(position)
            }
        }

        viewModel.times.observe(viewLifecycleOwner) { timeslots ->
            timeAdapter.submitList(timeslots)
            sharedViewModel.setSelectedTimeSlot(timeslots.find { it.isSelected })
        }
    }

    override fun stopOps() {}
}