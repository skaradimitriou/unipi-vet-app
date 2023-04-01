package com.example.unipivetapp.util.ext

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.unipivetapp.databinding.AskUserBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Helper method to set the screen title inside a [Fragment] in a more simple way.
 */

fun Fragment.setScreenTitle(title: String) {
    requireActivity().title = title
}

/**
 * Helper fun to simplify the addition of a custom menu inside a [Fragment].
 */

fun Fragment.addAppBarMenu(menuId: Int, selectedOption: (Int) -> Unit) {
    val menuHost: MenuHost = requireActivity()

    menuHost.addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            // Add menu items here
            menuInflater.inflate(menuId, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            selectedOption.invoke(menuItem.itemId)
            return true
        }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
}

fun Fragment.askUserForAction(title: String, btnTitle: String, onPrimaryBtnClick: () -> Unit) {
    requireContext().askUserForAction(title, btnTitle, onPrimaryBtnClick)
}

fun Context.askUserForAction(title: String, btnTitle: String, onPrimaryBtnClick: () -> Unit) {
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

/**
 * Helper fun to simplify the activity result api for Camera purposes.
 */

fun Fragment.onSuccessCameraResult(data: (Bitmap?) -> Unit) = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val bitmap = result.data?.extras?.get("data") as? Bitmap
        data.invoke(bitmap)
    }
}