package com.example.unipivetapp.util.ext

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Helper method to show a simple [Snackbar].
 */

fun ViewDataBinding.showSnackbar(message: String) {
    Snackbar.make(this.root, message, Snackbar.LENGTH_LONG).show()
}

inline fun <reified T : Parcelable> Intent.getParcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}