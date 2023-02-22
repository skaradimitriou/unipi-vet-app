package com.example.unipivetapp.util.ext

import androidx.fragment.app.Fragment

/**
 * Helper method to set the screen title inside a [Fragment] in a more simple way.
 */

fun Fragment.setScreenTitle(title: String) {
    requireActivity().title = title
}