package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Request

interface RequestRemoteDataSource {
    fun getRequest(): Request

    suspend fun getAllRequests(): List<Request>

    suspend fun updateRequest(id: String, requestProperties: String): HostActionStatus

    suspend fun removeRequest(requestId: String): HostActionStatus
}