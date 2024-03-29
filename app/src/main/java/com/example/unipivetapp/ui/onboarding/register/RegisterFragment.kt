package com.example.unipivetapp.ui.onboarding.register

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.domain.models.Result
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentRegisterBinding
import com.example.unipivetapp.ui.prehome.PreHomeActivity
import com.example.unipivetapp.util.ext.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun init() {
        binding.registerBtn.setOnClickListener {
            val email = binding.emailInputField.text.toString()
            val pass = binding.passInputField.text.toString()
            val confirmPass = binding.passConfInputField.text.toString()

            viewModel.validateData(email, pass, confirmPass)
        }
    }

    override fun startOps() {
        viewModel.registrationResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> binding.registerBtn.isEnabled = false
                is Result.Success -> goToAppOnboarding()
                is Result.Failure -> {
                    binding.showSnackbar(result.error.toString())
                    binding.registerBtn.isEnabled = true
                }
            }
        }
    }

    override fun stopOps() {}

    private fun goToAppOnboarding() {
        startActivity(Intent(requireContext(), PreHomeActivity::class.java))
        requireActivity().finish()
    }
}