package com.example.unipivetapp.ui.pets.add

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(R.layout.fragment_add) {

    private val viewModel: AddViewModel by viewModels()

    override fun init() {

    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}