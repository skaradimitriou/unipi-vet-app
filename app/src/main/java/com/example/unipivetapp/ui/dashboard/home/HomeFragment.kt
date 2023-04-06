package com.example.unipivetapp.ui.dashboard.home

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.domain.models.FeaturedItem
import com.example.domain.models.Result
import com.example.domain.models.Vet
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentHomeBinding
import com.example.unipivetapp.ui.dashboard.home.adapter.HomeAdapter
import com.example.unipivetapp.ui.dashboard.home.adapter.HomeScreenCallback
import com.example.unipivetapp.ui.docdetails.DocDetailsActivity
import com.example.unipivetapp.util.VET
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), HomeScreenCallback {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter = HomeAdapter(this)

    override fun init() {
        setScreenTitle(getString(R.string.home_title))
        binding.adapter = adapter
    }

    override fun startOps() {
        viewModel.getDashboardData()
        viewModel.dashboardData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> binding.isLoading = true
                is Result.Success -> {
                    binding.isLoading = false
                    adapter.submitList(result.data?.toUiData())
                }
                is Result.Failure -> binding.isLoading = false
            }
        }
    }

    override fun stopOps() {}

    override fun onFeaturedItemClick(item: FeaturedItem) {
        //FIXME: Respond to clicks later on
    }

    override fun onVetClick(vet: Vet) {
        startActivity(Intent(requireContext(), DocDetailsActivity::class.java).apply {
            putExtra(VET, vet)
        })
    }
}