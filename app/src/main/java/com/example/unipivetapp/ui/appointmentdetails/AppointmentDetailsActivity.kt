package com.example.unipivetapp.ui.appointmentdetails

import android.view.MenuItem
import androidx.activity.viewModels
import com.example.domain.models.AppointmentInfo
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityAppointmentDetailsBinding
import com.example.unipivetapp.util.APPOINTMENT
import com.example.unipivetapp.util.ext.askUserForAction
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppointmentDetailsActivity :
    BaseActivity<ActivityAppointmentDetailsBinding>(R.layout.activity_appointment_details) {

    @Inject
    lateinit var gson: Gson

    private val viewModel: AppointmentDetailsViewModel by viewModels()

    override fun init() {
        title = getString(R.string.appointment_info_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getStringExtra(APPOINTMENT)?.let {
            val appointment = gson.fromJson(it, AppointmentInfo::class.java)
            binding.appointment = appointment
            viewModel.appointment = appointment
        }

        binding.continueBtn.setOnClickListener {
            askUserForAction(
                title = getString(R.string.appointment_details_bs_title),
                btnTitle = getString(R.string.appointment_details_bs_cancel)
            ) {
                viewModel.cancelAppointment()
            }
        }
    }

    override fun startOps() {
        viewModel.appointmentDeleted.observe(this) { result ->
            when (result) {
                is com.example.domain.models.Result.Success -> {
                    setResult(RESULT_OK)
                    finish()
                }
                else -> Unit
            }
        }
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            false
        }
        else -> false
    }
}