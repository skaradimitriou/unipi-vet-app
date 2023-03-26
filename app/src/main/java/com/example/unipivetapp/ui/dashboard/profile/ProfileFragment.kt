package com.example.unipivetapp.ui.dashboard.profile

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentProfileBinding
import com.example.unipivetapp.ui.dashboard.profile.adapter.ProfileOptionsAdapter
import com.example.unipivetapp.ui.onboarding.OnboardingActivity
import com.example.unipivetapp.util.ext.askUserForAction
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    private val adapter = ProfileOptionsAdapter {
        askUserForAction(
            title = getString(R.string.ask_user_logout),
            btnTitle = getString(R.string.logout)
        ) {
            viewModel.logoutUser()
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
            adapter.submitList(uiData)
        }

        viewModel.userLoggedOut.observe(viewLifecycleOwner) { loggedOut ->
            startActivity(Intent(requireContext(), OnboardingActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun stopOps() {}
}