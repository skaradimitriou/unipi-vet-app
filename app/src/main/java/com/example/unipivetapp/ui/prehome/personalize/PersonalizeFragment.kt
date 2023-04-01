package com.example.unipivetapp.ui.prehome.personalize

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentPersonalizeBinding
import com.example.unipivetapp.ui.prehome.PreHomeViewModel
import com.example.unipivetapp.ui.prehome.navigator.PreHomeAction

class PersonalizeFragment :
    BaseFragment<FragmentPersonalizeBinding>(R.layout.fragment_personalize) {

    private val viewModel: PersonalizeViewModel by viewModels()
    private val sharedViewModel: PreHomeViewModel by activityViewModels()

    override fun init() {
        binding.profileUserImgView.setOnClickListener {

        }

        binding.saveBtn.setOnClickListener {
            sharedViewModel.navigateToScreen(PreHomeAction.ADDITIONAL)
        }
    }

    override fun startOps() {

    }

    override fun stopOps() {}
}