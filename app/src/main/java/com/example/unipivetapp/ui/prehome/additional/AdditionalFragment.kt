package com.example.unipivetapp.ui.prehome.additional

import android.content.Intent
import androidx.fragment.app.activityViewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAdditionalBinding
import com.example.unipivetapp.ui.dashboard.DashboardActivity
import com.example.unipivetapp.ui.prehome.PreHomeSharedViewModel
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdditionalFragment : BaseFragment<FragmentAdditionalBinding>(R.layout.fragment_additional) {

    private val sharedViewModel: PreHomeSharedViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.pre_home_additionals_title))
    }

    override fun startOps() {
        binding.primaryBtn.setOnClickListener {
            sharedViewModel.setTelephone(binding.telEditTxt.text.toString())
            sharedViewModel.setFirstName(binding.fNameEditTxt.text.toString())
            sharedViewModel.setLastName(binding.lNameEditTxt.text.toString())
            sharedViewModel.saveUserData()
        }

        sharedViewModel.userDataSaved.observe(viewLifecycleOwner) { savedSuccessfully ->
            if (savedSuccessfully) goToDashboard()
        }
    }

    override fun stopOps() {}

    private fun goToDashboard() {
        startActivity(Intent(requireContext(), DashboardActivity::class.java))
        requireActivity().finish()
    }
}