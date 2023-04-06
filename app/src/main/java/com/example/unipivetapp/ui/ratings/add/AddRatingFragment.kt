package com.example.unipivetapp.ui.ratings.add

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAddRatingBinding
import com.example.unipivetapp.ui.ratings.RatingsViewModel
import com.example.unipivetapp.ui.ratings.navigator.RatingsAction
import com.example.unipivetapp.util.DOC_ID
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRatingFragment : BaseFragment<FragmentAddRatingBinding>(R.layout.fragment_add_rating) {

    private val viewModel: AddRatingViewModel by viewModels()
    private val sharedViewModel: RatingsViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.new_review_title))

        val docId = requireActivity().intent.getIntExtra(DOC_ID, 0)
        viewModel.setDoctorId(docId)

        binding.ratingTitleEditTxt.doAfterTextChanged { title ->
            viewModel.validateInput(title.toString())
        }

        binding.saveBtn.setOnClickListener {
            val title = binding.ratingTitleEditTxt.text.toString()
            val description = binding.ratingDescEditTxt.text.toString()
            val rating = binding.ratingBar.rating.toDouble()
            viewModel.setRating(title, description, rating)
        }
    }

    override fun startOps() {
        viewModel.ratingSaved.observe(viewLifecycleOwner) { ratingSaved ->
            if (ratingSaved) {
                sharedViewModel.navigateToScreen(RatingsAction.NEW_RATING_ADDED)
            }
        }

        viewModel.validRating.observe(viewLifecycleOwner) { isValid ->
            binding.ctaEnabled = isValid
        }
    }

    override fun stopOps() {
        //
    }
}