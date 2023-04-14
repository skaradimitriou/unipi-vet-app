package com.example.unipivetapp.util

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.unipivetapp.R
import com.example.unipivetapp.ui.pets.add.uimodel.PetChip
import com.example.unipivetapp.util.ext.getActualColor
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

@BindingAdapter("setVetRatingTxt")
fun TextView.setVetRatingTxt(rate: Double?) {
    text = resources.getString(R.string.vet_rating_placeholder, rate.toString())
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
fun ViewPager2.setViewPagerPageTransformer(shouldBeSet: Boolean) {
    offscreenPageLimit = 3

    val compositePageTransformer = CompositePageTransformer()
    compositePageTransformer.addTransformer(MarginPageTransformer(25))
    compositePageTransformer.addTransformer { page: View, position: Float ->
        val r = 1 - abs(position)
        page.scaleY = 0.85f + r * 0.15f
    }
    setPageTransformer(compositePageTransformer)
}

@BindingAdapter("setRatingDescription")
fun TextView.setRatingDescription(description: String) {
    text = description
    visibility = if (description.isNotEmpty()) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("setChipBackground")
fun TextView.setChipBackground(chip: PetChip) {
    background = ResourcesCompat.getDrawable(resources, R.drawable.round_corners, context.theme)
    setTextColor(context.getActualColor(R.color.white))

    backgroundTintList = if (chip.isSelected) {
        ColorStateList.valueOf(context.getActualColor(R.color.dark_blue))
    } else {
        ColorStateList.valueOf(context.getActualColor(R.color.grey_dark_bg))
    }
}