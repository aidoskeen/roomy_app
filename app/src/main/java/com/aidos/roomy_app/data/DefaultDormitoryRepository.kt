package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.DormitoryRemoteDataSource
import com.aidos.roomy_app.frameworks.dagger.subcomponents.ApplicationScope
import com.aidos.roomy_app.models.Dormitory
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DefaultDormitoryRepository @Inject constructor(
    private val dataSource: DormitoryRemoteDataSource,
    @ApplicationScope private val scope: CoroutineScope,
): DormitoryRepository {
    override fun getDormitories(): List<Dormitory> = dataSource.getDormitories()

    override fun getDormitory(id: Int): Dormitory = dataSource.getDormitoryById(id)

}