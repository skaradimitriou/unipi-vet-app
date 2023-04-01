package com.example.unipivetapp.ui.prehome.navigator

interface PreHomeNavigator {

    fun navigateTo(screenKey: PreHomeAction)
    fun goBack()
}