package com.example.unipivetapp.util.ext

import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.unipivetapp.databinding.AskUserBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

/**
 * Helper method to set the screen title inside a [Fragment] in a more simple way.
 */

fun Fragment.setScreenTitle(title: String) {
    requireActivity().title = title
}

fun Context.askUserForAction(title: String, btnTitle : String, onPrimaryBtnClick: () -> Unit) {
    val binding = AskUserBottomsheetBinding.inflate(LayoutInflater.from(this))
    BottomSheetDialog(this).apply {
        setContentView(binding.root)
        binding.headerTxtView.text = title
        binding.primaryBtn.text = btnTitle
        binding.primaryBtn.setOnClickListener {
            onPrimaryBtnClick.invoke()
            dismiss()
        }

        binding.secondaryBtn.setOnClickListener {
            dismiss()
        }
    }.show()
}