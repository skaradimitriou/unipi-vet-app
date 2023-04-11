package com.example.unipivetapp.util.ext

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import com.example.unipivetapp.R
import com.google.android.material.snackbar.Snackbar
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

fun TextView.setMonthName() {
    val format = DateTimeFormatter.ofPattern("MMMM")
    val month = LocalDate.now()?.format(format).toString()
    text = context.getString(R.string.appointment_month_placeholder, month)
}

fun NavController.init(navigationId: Int, destinationId: Int) {
    val navGraph = navInflater.inflate(navigationId)
    navGraph.setStartDestination(destinationId)
    graph = navGraph
}

/**
 * Helper fun to get colors from resources
 */

fun Context.getActualColor(color: Int): Int = ContextCompat.getColor(this, color)