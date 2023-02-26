package com.example.data.di

import com.example.data.repositories.AppointmentRepositoryImpl
import com.example.data.repositories.ProfileRepositoryImpl
import com.example.data.repositories.VetRepositoryImpl
import com.example.domain.repositories.AppointmentRepository
import com.example.domain.repositories.ProfileRepository
import com.example.domain.repositories.VetReponsitory
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideVetRepository(firestore: FirebaseFirestore): VetReponsitory =
        VetRepositoryImpl(firestore)

    @Provides
    fun provideProfileRepository(firestore: FirebaseFirestore): ProfileRepository =
        ProfileRepositoryImpl(firestore)

    @Provides
    fun provideAppointmentRepository(firestore: FirebaseFirestore): AppointmentRepository =
        AppointmentRepositoryImpl(firestore)
}