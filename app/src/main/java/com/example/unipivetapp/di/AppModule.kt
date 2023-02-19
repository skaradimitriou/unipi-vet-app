package com.example.unipivetapp.di

import com.example.unipivetapp.util.auth.AuthManager
import com.example.unipivetapp.util.auth.Authenticator
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthenticator(auth: FirebaseAuth): Authenticator = AuthManager(auth)
}