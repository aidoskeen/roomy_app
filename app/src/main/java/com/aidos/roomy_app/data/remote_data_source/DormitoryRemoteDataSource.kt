package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.Dormitory

interface DormitoryRemoteDataSource {

    suspend fun loadDormitories(): List<Dormitory>

    suspend fun saveDormitories(dormitories: List<Dormitory>)
}