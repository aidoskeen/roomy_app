package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.DormitoryRemoteDataSource
import javax.inject.Inject

class DefaultDormitoryRepository @Inject constructor(
    private val dataSource: DormitoryRemoteDataSource
) {
}