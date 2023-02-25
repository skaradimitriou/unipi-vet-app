package com.example.unipivetapp.util

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.unipivetapp.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * This file contains the binding adapters that are used across the app
 */

@BindingAdapter("imageFromUrl")
fun ImageView.setImageFromUrl(url: String?) {
    try {
        Glide.with(this).load(url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(this)
    } catch (e: Exception) {
        setImageResource(R.mipmap.ic_launcher)
    }
}

@BindingAdapter("setProfileImage")
fun ImageView.setProfileImage(url: String?) {
    try {
        Glide.with(this).load(url)
            .placeholder(R.drawable.profile_placeholder)
            .error(R.drawable.profile_placeholder)
            .into(this)
    } catch (e: Exception) {
        setImageResource(R.drawable.profile_placeholder)
    }
}

@BindingAdapter("setProfileOptionValue")
fun TextView.setProfileOptionValue(value: String?) {
    text = if (value.isNullOrEmpty()) {
        resources.getString(R.string.profile_option_missing)
    } else {
        value
    }
}

@BindingAdapter("setVetRating")
fun RatingBar.setVetRating(rate: Double?) {
    rating = rate?.toFloat() ?: 0F
}

@BindingAdapter("setWorkingExperience")
fun TextView.setWorkingExperience(experience: Int?) {
    text = resources.getString(R.string.working_experience, experience)
}

@BindingAdapter("setCalendarDayOfMonth")
fun TextView.setCalendarDayOfMonth(date: LocalDate) {
    text = date.dayOfMonth.toString()
}

@BindingAdapter("setDayOfMonth")
fun TextView.setDayOfMonth(date: LocalDate) {
    val format1 = DateTimeFormatter.ofPattern("EEE")
    text = date.format(format1)
}