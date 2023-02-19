package com.example.unipivetapp.ui.dashboard.vets

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentVetsBinding
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VetsFragment : BaseFragment<FragmentVetsBinding>(R.layout.fragment_vets) {

    private val viewModel: VetsViewModel by viewModels()

    override fun init() {
        setScreenTitle("Vets")
    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}