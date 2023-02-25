package com.example.unipivetapp.ui.docdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.UiModel
import com.example.domain.models.Vet
import com.example.unipivetapp.BR
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderEmptyItemBinding
import com.example.unipivetapp.databinding.HolderVetAppointmentBinding
import com.example.unipivetapp.databinding.HolderVetDetailsItemBinding
import com.example.unipivetapp.databinding.HolderVetReviewBinding
import com.example.unipivetapp.ui.docdetails.uimodel.DocAppointment
import com.example.unipivetapp.ui.docdetails.uimodel.DocHeader
import com.example.unipivetapp.ui.docdetails.uimodel.DocReview

class DocDetailsAdapter(
    private val callback: DocDetailsCallback
) : ListAdapter<UiModel, DocDetailsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_vet_details_item -> {
                HolderVetDetailsItemBinding.inflate(inflater, parent, false)
            }
            R.layout.holder_vet_review -> {
                HolderVetReviewBinding.inflate(inflater, parent, false)
            }
            R.layout.holder_vet_appointment -> {
                HolderVetAppointmentBinding.inflate(inflater, parent, false)
            }
            else -> HolderEmptyItemBinding.inflate(inflater, parent, false)
        }
        return DocDetailsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DocDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is DocHeader -> R.layout.holder_vet_details_item
        is DocReview -> R.layout.holder_vet_review
        is DocAppointment -> R.layout.holder_vet_appointment
        else -> R.layout.holder_empty_item
    }
}

class DocDetailsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: DocDetailsCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is DocHeader, is DocReview, is DocAppointment -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
            else -> Unit
        }
    }
}

interface DocDetailsCallback {
    fun onReviewsClick()
    fun onAppointmentClick()
}