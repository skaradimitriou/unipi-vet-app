package com.example.unipivetapp.ui.pets.navigator

interface PetNavigator {

    fun navigateTo(screenKey: PetAction)
    fun goBack()
}