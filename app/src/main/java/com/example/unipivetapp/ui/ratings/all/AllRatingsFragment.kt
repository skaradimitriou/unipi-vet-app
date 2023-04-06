package com.example.unipivetapp.ui.ratings.all

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAllRatingsBinding
import com.example.unipivetapp.ui.ratings.RatingsViewModel
import com.example.unipivetapp.ui.ratings.all.adapter.RatingsAdapter
import com.example.unipivetapp.ui.ratings.navigator.RatingsAction
import com.example.unipivetapp.util.DOC_ID
import com.example.unipivetapp.util.ext.addAppBarMenu
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllRatingsFragment : BaseFragment<FragmentAllRatingsBinding>(R.layout.fragment_all_ratings) {

    private val viewModel: AllRatingsViewModel by viewModels()
    private val sharedViewModel: RatingsViewModel by activityViewModels()

    private val adapter = RatingsAdapter()

    override fun init() {
        setScreenTitle(getString(R.string.ratings_screen_title))
        addAppBarMenu(menuId = R.menu.ratings_menu) { selectedAction ->
            if (selectedAction == R.id.add_new_rating) {
                sharedViewModel.navigateToScreen(RatingsAction.ADD_NEW_RATING)
            }
        }
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