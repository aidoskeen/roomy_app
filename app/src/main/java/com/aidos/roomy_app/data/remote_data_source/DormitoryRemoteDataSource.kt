package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.Dormitory

interface DormitoryRemoteDataSource {

    fun getDormitories(): List<Dormitory>

    fun getDormitoryById(id: Int): Dormitory

    suspend fun saveDormitories(dormitories: List<Dormitory>)

    suspend fun deleteDormitory(id: String)

    suspend fun updateDormitory(id: String)
}