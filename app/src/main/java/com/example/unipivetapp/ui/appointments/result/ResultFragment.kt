package com.example.unipivetapp.ui.appointments.result

import android.content.Intent
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentResultBinding
import com.example.unipivetapp.ui.dashboard.DashboardActivity
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {

    override fun init() {
        setScreenTitle(getString(R.string.result_title))

        binding.continueBtn.setOnClickListener {
            val intent = Intent(requireContext(), DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun startOps() {}
    override fun stopOps() {}
}