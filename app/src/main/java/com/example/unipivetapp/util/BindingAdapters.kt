package com.example.unipivetapp.util

import android.widget.ImageView
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