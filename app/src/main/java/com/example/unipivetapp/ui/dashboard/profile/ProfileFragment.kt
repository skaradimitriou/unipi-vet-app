package com.example.unipivetapp.ui.dashboard.profile

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentProfileBinding
import com.example.unipivetapp.ui.dashboard.profile.adapter.ProfileOptionsAdapter
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileOptionType
import com.example.unipivetapp.ui.onboarding.OnboardingActivity
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()
    private val adapter = ProfileOptionsAdapter { selectedOption ->
        when (selectedOption.type) {
            ProfileOptionType.LOGOUT -> viewModel.logoutUser()
            else -> Unit
        }
    }

    override fun init() {
        setScreenTitle(getString(R.string.profile_title))

        binding.viewModel = viewModel
        binding.profileDetailsRecycler.adapter = adapter
    }

    override fun startOps() {
        viewModel.getProfileInfo()

        viewModel.userInfo.observe(viewLifecycleOwner) { uiData ->
            adapter.submitList(uiData.items)
        }

        viewModel.userLoggedOut.observe(viewLifecycleOwner) { loggedOut ->
            startActivity(Intent(requireContext(), OnboardingActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun stopOps() {}
}