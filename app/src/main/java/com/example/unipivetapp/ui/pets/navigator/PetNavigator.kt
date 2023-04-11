package com.example.unipivetapp.ui.pets.navigator

interface PetNavigator {

    fun init(screenKey: PetAction)
    fun navigateTo(screenKey: PetAction)
    fun goBack()
}