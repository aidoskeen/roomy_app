package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.RequestRemoteDataSource
import javax.inject.Inject

class DefaultRequestRepository @Inject constructor(
    private val dataSource: RequestRemoteDataSource
) {
}