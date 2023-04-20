package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Request

interface RequestRepository {

    suspend fun getRequest(): Request
    suspend fun getAllRequests(): List<Request>
    suspend fun updateRequestStatus(request: Request): HostActionStatus
    suspend fun removeRequest(id: String, request: Request): HostActionStatus

}


