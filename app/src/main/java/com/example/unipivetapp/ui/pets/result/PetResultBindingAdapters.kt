package com.example.unipivetapp.ui.pets.result

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.unipivetapp.R
import com.example.unipivetapp.ui.pets.navigator.PetAction

@BindingAdapter("setPetResultTitle")
fun TextView.setPetResultTitle(action: PetAction?) {
    text = when (action) {
        PetAction.ADD_PET_RESULT -> context.getString(R.string.pet_result_title)
        PetAction.EDIT_PET_RESULT -> context.getString(R.string.pet_result_update_title)
        else -> ""
    }
}

@BindingAdapter("setPetResultDesc")
fun TextView.setPetResultDesc(action: PetAction?) {
    text = when (action) {
        PetAction.ADD_PET_RESULT -> context.getString(R.string.pet_result_desc)
        PetAction.EDIT_PET_RESULT -> context.getString(R.string.pet_result_update_desc)
        else -> ""
    }
}