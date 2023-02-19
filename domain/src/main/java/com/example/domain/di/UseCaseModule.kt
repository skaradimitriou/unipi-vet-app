package com.example.domain.di

import com.example.domain.repositories.VetReponsitory
import com.example.domain.usecases.GetAllVetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllVetsUseCase(repo: VetReponsitory) = GetAllVetsUseCase(repo)
}