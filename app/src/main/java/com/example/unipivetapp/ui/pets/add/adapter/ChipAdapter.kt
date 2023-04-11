package com.example.unipivetapp.ui.pets.add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderChipItemBinding
import com.example.unipivetapp.ui.pets.add.uimodel.PetChip

class ChipAdapter(
    private val callback: ChipCallback
) : ListAdapter<PetChip, ChipAdapter.ChipViewHolder>(DiffUtilClass<PetChip>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val view = HolderChipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChipViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ChipViewHolder(
        private val binding: HolderChipItemBinding,
        private val callback: ChipCallback
    ) : BaseViewHolder(binding) {

        override fun present(data: UiModel) {
            when (data) {
                is PetChip -> {
                    binding.model = data

                    binding.chipTxtView.setOnClickListener {
                        val item = (currentList.find { it.isSelected })
                        item?.let {
                            item.isSelected = !item.isSelected
                            notifyItemChanged(currentList.indexOf(item))
                        }

                        data.isSelected = !data.isSelected
                        notifyItemChanged(currentList.indexOf(data))
                        callback.onChipClick(data)
                    }
                }
            }
        }
    }
}

fun interface ChipCallback {
    fun onChipClick(chip: PetChip)
}