package com.example.unipivetapp.ui.dashboard.profile

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentProfileBinding
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    /*
     * FIXME: 1. Finalize strings to be displayed.
     *        2. Connect adapter with viewmodel data
     *        3. add logout functionality on logout btn click.
     */

    override fun init() {
        setScreenTitle("Προφίλ")
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.getProfileInfo()
    }

    override fun stopOps() {}
}