package com.example.unipivetapp.ui.onboarding.intro

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentIntroBinding
import com.example.unipivetapp.ui.dashboard.DashboardActivity
import com.example.unipivetapp.ui.onboarding.OnboardingSharedViewModel
import com.example.unipivetapp.ui.onboarding.navigator.OnboardingAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFragment : BaseFragment<FragmentIntroBinding>(R.layout.fragment_intro) {

    private val viewModel: IntroViewModel by viewModels()
    private val sharedViewModel: OnboardingSharedViewModel by activityViewModels()

    override fun init() {}

    override fun startOps() {
        viewModel.getCurrentUser()
        viewModel.userIsLoggedIn.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                startActivity(Intent(requireContext(), DashboardActivity::class.java))
                requireActivity().finish()
            } else {
                sharedViewModel.navigateToScreen(OnboardingAction.LOGIN)
            }
        }
    }

    override fun stopOps() {}
}