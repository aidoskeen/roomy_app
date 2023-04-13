package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.Request

interface RequestRepository {

    suspend fun getRequest(): Request
    suspend fun getAllRequests(): List<Request>
    suspend fun updateRequest(id: String, request: Request)
    suspend fun removeRequest(id: String, request: Request)

}


