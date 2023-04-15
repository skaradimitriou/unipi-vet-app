package com.example.data.di

import android.app.Application
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
    fun provideAppointmentRepository(
        app: Application,
        firestore: FirebaseFirestore,
        notificationsRepository: NotificationsRepository
    ): AppointmentRepository =
        AppointmentRepositoryImpl(app, firestore, notificationsRepository)

    @Provides
    @Singleton
    fun provideDashboardRepository(firestore: FirebaseFirestore): DashboardRepository =
        DashboardRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideRatingsRepository(
        app: Application,
        firestore: FirebaseFirestore,
        notificationsRepository: NotificationsRepository
    ): RatingsRepository =
        RatingsRepositoryImpl(app, firestore, notificationsRepository)

    @Provides
    @Singleton
    fun providePetRepository(
        app: Application,
        firestore: FirebaseFirestore,
        storage: StorageReference,
        notificationsRepository: NotificationsRepository
    ): PetRepository =
        PetRepositoryImpl(app, firestore, storage, notificationsRepository)

    @Provides
    @Singleton
    fun provideNotificationsRepository(firestore: FirebaseFirestore): NotificationsRepository =
        NotificationsRepositoryImpl(firestore)
}