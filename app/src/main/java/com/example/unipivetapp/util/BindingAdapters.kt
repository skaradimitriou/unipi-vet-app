package com.example.unipivetapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.unipivetapp.R

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
        "Not provided yet."
    } else {
        value
    }
}