package com.example.unipivetapp.ui.onboarding.login

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.domain.models.Result
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentLoginBinding
import com.example.unipivetapp.ui.dashboard.DashboardActivity
import com.example.unipivetapp.ui.onboarding.OnboardingSharedViewModel
import com.example.unipivetapp.ui.onboarding.navigator.OnboardingAction
import com.example.unipivetapp.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    private val sharedViewModel: OnboardingSharedViewModel by activityViewModels()

    override fun init() {
        binding.loginBtn.setOnClickListener {
            val email = binding.emailInputField.text.toString()
            val pass = binding.passInputField.text.toString()

            viewModel.login(email, pass)
        }

        binding.registerBtn.setOnClickListener {
            sharedViewModel.navigateToScreen(OnboardingAction.REGISTER)
        }
    }

    override fun startOps() {
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> binding.loginBtn.isEnabled = false
                is Result.Success -> goToDashboard()
                is Result.Failure -> {
                    binding.loginBtn.isEnabled = true
                    binding.showSnackbar(result.error.toString())
                }
            }
        }
    }

    override fun stopOps() {}

    private fun goToDashboard() {
        startActivity(Intent(requireContext(), DashboardActivity::class.java))
        requireActivity().finish()
    }
}