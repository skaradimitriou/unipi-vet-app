package com.example.unipivetapp.ui.ratings.navigator

interface RatingsNavigator {
    fun navigateTo(screenKey: RatingsAction)
    fun goBack()
}