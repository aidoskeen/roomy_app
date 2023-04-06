package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.RequestRemoteDataSource
import com.aidos.roomy_app.models.Request
import javax.inject.Inject

class DefaultRequestRepository @Inject constructor(
    private val dataSource: RequestRemoteDataSource
) : RequestRepository {
    override fun getRequest(): Request {
        TODO("Not yet implemented")
    }

    override fun getAllRequests(): List<Request> {
        TODO("Not yet implemented")
    }

    override fun updateRequest(id: String, request: Request) {
        TODO("Not yet implemented")
    }

    override fun removeRequest(id: String, request: Request) {
        TODO("Not yet implemented")
    }
}