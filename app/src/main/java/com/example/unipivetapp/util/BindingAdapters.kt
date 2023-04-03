package com.example.unipivetapp.util

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.unipivetapp.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.abs

/**
 * This file contains the binding adapters that are used across the app
 */

@BindingAdapter("imageFromUrl")
fun ImageView.setImageFromUrl(url: String?) {
    try {
        Glide.with(this).load(url)
            .placeholder(R.mipmap.myvet_logo)
            .error(R.mipmap.myvet_logo)
            .into(this)
    } catch (e: Exception) {
        setImageResource(R.mipmap.myvet_logo)
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
fun TextView.setCalendarDayOfMonth(date: LocalDate?) {
    text = date?.dayOfMonth.toString()
}

@BindingAdapter("setDayOfMonth")
fun TextView.setDayOfMonth(date: LocalDate?) {
    val format1 = DateTimeFormatter.ofPattern("EEE")
    text = date?.format(format1).toString()
}

@BindingAdapter("setAppointmentDate")
fun TextView.setAppointmentDate(date: LocalDate?) {
    val format1 = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
    text = date?.format(format1).toString()
}

@BindingAdapter("setAppointmentInfoDate")
fun TextView.setAppointmentInfoDate(date: String) {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    val outputDate = input.parse(date)
    val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy")
    text = sdf.format(outputDate).toString()
}

@BindingAdapter("setAppointmentInfoTime")
fun TextView.setAppointmentInfoTime(time: String) {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    val outputTime = input.parse(time)
    val sdf = SimpleDateFormat("HH:mm")
    text = sdf.format(outputTime).toString()
}

@BindingAdapter("setViewPagerPageTransformer")
fun ViewPager2.setViewPagerPageTransformer(shouldBeSet : Boolean) {
    val compositePageTransformer = CompositePageTransformer()
    compositePageTransformer.addTransformer(MarginPageTransformer(40))
    compositePageTransformer.addTransformer { page: View, position: Float ->
        val r = 1 - abs(position)
        page.scaleY = 0.85f + r * 0.15f
    }
    this.setPageTransformer(compositePageTransformer)
}