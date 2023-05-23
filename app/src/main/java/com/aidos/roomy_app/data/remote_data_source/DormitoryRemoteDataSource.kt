package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.data.ResponseModels.DormitoryResponse
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Announcement
import com.aidos.roomy_app.models.Dormitory

interface DormitoryRemoteDataSource {

    suspend fun getDormitories(): List<Dormitory>

    suspend fun createAnnouncement(announcement: Announcement) : HostActionStatus
    suspend fun getDormitoryById(id: Int): Dormitory

    suspend fun saveDormitories(dormitories: List<Dormitory>)

    suspend fun deleteDormitory(id: String)

    suspend fun updateDormitory(id: String)
}