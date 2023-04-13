package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.RequestRemoteDataSource
import com.aidos.roomy_app.models.Request
import javax.inject.Inject

class DefaultRequestRepository @Inject constructor(
    private val dataSource: RequestRemoteDataSource
) : RequestRepository {
    override suspend fun getRequest(): Request = dataSource.getRequest()

    override suspend fun getAllRequests(): List<Request> = dataSource.getAllRequests()

    override suspend fun updateRequest(id: String, request: Request) = dataSource.updateRequest(id, request)

    override suspend fun removeRequest(id: String, request: Request) = dataSource.removeRequest(id, request)
}