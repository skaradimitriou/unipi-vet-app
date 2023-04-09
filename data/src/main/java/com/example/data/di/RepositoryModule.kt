package com.example.data.di

import com.example.data.repositories.*
import com.example.domain.repositories.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideVetRepository(firestore: FirebaseFirestore): VetReponsitory =
        VetRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideProfileRepository(
        firestore: FirebaseFirestore,
        storageReference: StorageReference
    ): ProfileRepository = ProfileRepositoryImpl(firestore, storageReference)

    @Provides
    @Singleton
    fun provideAppointmentRepository(firestore: FirebaseFirestore): AppointmentRepository =
        AppointmentRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideDashboardRepository(firestore: FirebaseFirestore): DashboardRepository =
        DashboardRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideRatingsRepository(firestore: FirebaseFirestore): RatingsRepository =
        RatingsRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun providePetRepository(
        firestore: FirebaseFirestore,
        storage: StorageReference
    ): PetRepository =
        PetRepositoryImpl(firestore, storage)
}