package com.example.domain.repositories


import com.example.domain.models.DashboardModel

interface DashboardRepository {

    suspend fun getDashboardData(): DashboardModel
}