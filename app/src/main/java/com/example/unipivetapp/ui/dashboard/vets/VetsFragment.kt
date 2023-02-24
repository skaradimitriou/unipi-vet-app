package com.example.unipivetapp.ui.dashboard.vets

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentVetsBinding
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class VetsFragment : BaseFragment<FragmentVetsBinding>(R.layout.fragment_vets) {

    private val viewModel: VetsViewModel by viewModels()

    private val adapter = VetAdapter { selectedVet ->
        Timber.d("$selectedVet")
    }

    override fun init() {
        setScreenTitle(getString(R.string.vets_title))
        binding.adapter = adapter
    }

    override fun startOps() {
        viewModel.getAllVets()

        viewModel.vets.observe(viewLifecycleOwner) { vets ->
            adapter.submitList(vets)
        }
    }

    override fun stopOps() {}
}