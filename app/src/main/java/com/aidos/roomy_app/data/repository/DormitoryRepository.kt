package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.models.Dormitory

interface DormitoryRepository {

    suspend fun getDormitories(): List<Dormitory>

    suspend fun getDormitory(id: Int): Dormitory

}