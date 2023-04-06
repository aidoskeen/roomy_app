package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.Request

interface RequestRepository {

    fun getRequest(): Request

    fun getAllRequests(): List<Request>

    fun updateRequest(id: String, request: Request)

    fun removeRequest(id: String, request: Request)
}

