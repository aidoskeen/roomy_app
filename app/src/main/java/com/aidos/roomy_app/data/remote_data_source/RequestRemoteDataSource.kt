package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.Request

interface RequestRemoteDataSource {
    fun getRequest(): Request

    fun getAllRequests(): List<Request>

    fun updateRequest(id: String, request: Request)

    fun removeRequest(id: String, request: Request)
}