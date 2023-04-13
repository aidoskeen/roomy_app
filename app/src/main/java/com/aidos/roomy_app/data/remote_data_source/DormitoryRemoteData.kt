package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.Dormitory
import javax.inject.Inject

class DormitoryRemoteData @Inject constructor(

) : DormitoryRemoteDataSource{
    override suspend fun getDormitories(): List<Dormitory> {
        TODO("Not yet implemented")
    }

    override suspend fun getDormitoryById(id: Int): Dormitory {
        TODO("Not yet implemented")
    }

    override suspend fun saveDormitories(dormitories: List<Dormitory>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDormitory(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDormitory(id: String) {
        TODO("Not yet implemented")
    }
}