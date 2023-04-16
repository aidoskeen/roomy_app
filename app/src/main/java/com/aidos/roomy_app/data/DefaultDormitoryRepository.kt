package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.DormitoryRemoteDataSource
import com.aidos.roomy_app.frameworks.dagger.subcomponents.ApplicationScope
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.Dormitory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultDormitoryRepository @Inject constructor(
    private val dataSource: DormitoryRemoteDataSource
): DormitoryRepository {
    override suspend fun getDormitories(): List<Dormitory> {
        return dataSource.getDormitories().map {
            Dormitory(
                it.dormitoryId,
                it.address,
                university = it.university
            )
        }
    }


    override suspend fun getDormitory(id: Int): Dormitory = dataSource.getDormitoryById(id)

}