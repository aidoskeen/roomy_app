package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.DormitoryRemoteDataSource
import com.aidos.roomy_app.models.Dormitory
import javax.inject.Inject

class DefaultDormitoryRepository @Inject constructor(
    private val dataSource: DormitoryRemoteDataSource
): DormitoryRepository {
    override suspend fun getDormitories(): List<Dormitory> = dataSource.getDormitories()

    override suspend fun getDormitory(id: Int): Dormitory = dataSource.getDormitoryById(id)

}