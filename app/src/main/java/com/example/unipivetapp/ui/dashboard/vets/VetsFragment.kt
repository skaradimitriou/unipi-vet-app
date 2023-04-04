package com.example.unipivetapp.ui.dashboard.vets

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentVetsBinding
import com.example.unipivetapp.ui.docdetails.DocDetailsActivity
import com.example.unipivetapp.util.STANDARD_DELAY
import com.example.unipivetapp.util.VET
import com.example.unipivetapp.util.ext.addSearchBarMenu
import com.example.unipivetapp.util.ext.setScreenTitle
import com.example.unipivetapp.util.ext.withDelay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VetsFragment : BaseFragment<FragmentVetsBinding>(R.layout.fragment_vets) {

    private val viewModel: VetsViewModel by viewModels()

    private val adapter = VetAdapter { selectedVet ->
        startActivity(Intent(requireContext(), DocDetailsActivity::class.java).apply {
            putExtra(VET, selectedVet)
        })
    }

    override fun init() {
        setScreenTitle(getString(R.string.vets_title))
        addSearchBarMenu(menuId = R.menu.vet_menu) { query ->
            viewModel.handleQuery(query)
        }
        binding.adapter = adapter
    }

    override fun startOps() {
        viewModel.getAllVets()

        viewModel.vets.observe(viewLifecycleOwner) { vets ->
            adapter.submitList(vets)
            withDelay(STANDARD_DELAY) {
                binding.vetsRecycler.scrollToPosition(0)
            }
        }
    }

    override fun stopOps() {}
}