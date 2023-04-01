package com.example.unipivetapp.util.ext

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

fun Fragment.withDelay(time: Long, action: () -> Unit) {
    lifecycleScope.launch {
        delay(time)
        action.invoke()
    }
}

fun TextView.setMonthName() {
    val format = DateTimeFormatter.ofPattern("MMMM")
    text = LocalDate.now()?.format(format).toString()
}