package com.example.data.di

import com.example.data.repositories.AppointmentRepositoryImpl
import com.example.data.repositories.DashboardRepositoryImpl
import com.example.data.repositories.ProfileRepositoryImpl
import com.example.data.repositories.VetRepositoryImpl
import com.example.domain.repositories.AppointmentRepository
import com.example.domain.repositories.DashboardRepository
import com.example.domain.repositories.ProfileRepository
import com.example.domain.repositories.VetReponsitory
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
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
    fun provideProfileRepository(
        firestore: FirebaseFirestore,
        storageReference: StorageReference
    ): ProfileRepository = ProfileRepositoryImpl(firestore, storageReference)

    @Provides
    fun provideAppointmentRepository(firestore: FirebaseFirestore): AppointmentRepository =
        AppointmentRepositoryImpl(firestore)

    @Provides
    fun provideDashboardRepository(firestore: FirebaseFirestore): DashboardRepository =
        DashboardRepositoryImpl(firestore)
}