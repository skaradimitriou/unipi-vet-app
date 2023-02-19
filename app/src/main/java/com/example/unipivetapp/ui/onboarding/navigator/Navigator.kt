package com.example.unipivetapp.ui.onboarding.navigator

interface Navigator {

    fun navigateTo(screenKey: OnboardingAction)
    fun goBack()
}