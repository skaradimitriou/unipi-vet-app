package com.example.unipivetapp.ui.ratings.all

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAllRatingsBinding
import com.example.unipivetapp.ui.ratings.all.adapter.RatingsAdapter
import com.example.unipivetapp.util.DOC_ID
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllRatingsFragment : BaseFragment<FragmentAllRatingsBinding>(R.layout.fragment_all_ratings) {

    private val viewModel: AllRatingsViewModel by viewModels()

    private val adapter = RatingsAdapter()

    override fun init() {
        setScreenTitle(getString(R.string.ratings_screen_title))
        binding.adapter = adapter
    }

    override fun startOps() {
        val docId = requireActivity().intent.getIntExtra(DOC_ID, 0)
        viewModel.getAllRatings(docId)

        viewModel.ratings.observe(viewLifecycleOwner) { data ->
            adapter.submitList(data)
        }
    }

    override fun stopOps() {}
}