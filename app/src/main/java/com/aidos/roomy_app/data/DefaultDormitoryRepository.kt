package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.DormitoryRemoteDataSource
import com.aidos.roomy_app.models.Dormitory
import javax.inject.Inject

class DefaultDormitoryRepository @Inject constructor(
    private val dataSource: DormitoryRemoteDataSource
): DormitoryRepository {
    override fun getDormitories(): List<Dormitory> {
        TODO("Not yet implemented")
    }

    override fun getDormitory(id: String): Dormitory {
        TODO("Not yet implemented")
    }

}