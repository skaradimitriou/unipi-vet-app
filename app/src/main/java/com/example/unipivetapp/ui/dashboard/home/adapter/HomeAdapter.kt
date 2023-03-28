package com.example.unipivetapp.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.*
import com.example.unipivetapp.BR
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderDashboardProfileBinding
import com.example.unipivetapp.databinding.HolderEmptyItemBinding
import com.example.unipivetapp.databinding.HolderFeaturedParentBinding
import com.example.unipivetapp.databinding.HolderVetParentBinding

class HomeAdapter(
    private val callback: HomeScreenCallback
) : ListAdapter<UiModel, HomeViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_dashboard_profile -> HolderDashboardProfileBinding.inflate(
                inflater, parent, false
            )
            R.layout.holder_featured_parent -> HolderFeaturedParentBinding.inflate(
                inflater, parent, false
            )
            R.layout.holder_vet_parent -> HolderVetParentBinding.inflate(inflater, parent, false)
            else -> HolderEmptyItemBinding.inflate(inflater, parent, false)
        }
        return HomeViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is UserInfo -> R.layout.holder_dashboard_profile
        is FeaturedItemParent -> R.layout.holder_featured_parent
        is VetItemParent -> R.layout.holder_vet_parent
        else -> R.layout.holder_empty_item
    }
}

class HomeViewHolder(
    private val binding: ViewDataBinding,
    private val callback: HomeScreenCallback
) : BaseViewHolder(binding), HomeScreenCallback {

    override fun present(data: UiModel) {
        when (data) {
            is UserInfo -> binding.setVariable(BR.model, data)
            is FeaturedItemParent -> {
                val adapter = HomeFeaturedAdapter(this)
                binding.setVariable(BR.adapter, adapter)
                adapter.submitList(data.items)
            }
            is VetItemParent -> {
                val adapter = HomeVetAdapter {
                    callback.onVetClick(it)
                }
                binding.setVariable(BR.adapter, adapter)
                adapter.submitList(data.items)
            }
        }
    }

    override fun onFeaturedItemClick(item: FeaturedItem) = callback.onFeaturedItemClick(item)
    override fun onVetClick(vet: Vet) = callback.onVetClick(vet)
}

interface HomeScreenCallback {
    fun onFeaturedItemClick(item: FeaturedItem)
    fun onVetClick(vet: Vet)
}