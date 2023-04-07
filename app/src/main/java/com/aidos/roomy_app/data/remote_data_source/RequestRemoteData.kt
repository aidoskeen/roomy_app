package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.Request
import javax.inject.Inject

class RequestRemoteData @Inject constructor(

) : RequestRemoteDataSource {
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