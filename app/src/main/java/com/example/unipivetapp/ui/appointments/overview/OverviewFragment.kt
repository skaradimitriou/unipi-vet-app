package com.example.unipivetapp.ui.appointments.overview

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentOverviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : BaseFragment<FragmentOverviewBinding>(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by viewModels()

    override fun init() {

    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}