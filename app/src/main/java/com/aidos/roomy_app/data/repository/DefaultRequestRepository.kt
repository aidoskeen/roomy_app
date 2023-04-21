package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.data.remote_data_source.RequestRemoteDataSource
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Request
import javax.inject.Inject

class DefaultRequestRepository @Inject constructor(
    private val dataSource: RequestRemoteDataSource
) : RequestRepository {
    override suspend fun getRequest(): Request = dataSource.getRequest()

    override suspend fun getAllRequests(): List<Request> = dataSource.getAllRequests()

    override suspend fun updateRequestStatus(request: Request): HostActionStatus {
        val requestProperties = "{${request.requestStatus}}"
        val requestId = request.requestId.toString()
        val response = dataSource.updateRequest(requestId, requestProperties)
        return response
    }

    override suspend fun removeRequest(id: String, request: Request): HostActionStatus = dataSource.removeRequest(id)
}

