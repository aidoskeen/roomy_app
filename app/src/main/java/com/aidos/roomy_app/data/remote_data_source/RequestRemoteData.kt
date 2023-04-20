package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.data.DeserializationTools.DormitoryDeserializer
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Request
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject

class RequestRemoteData @Inject constructor(
    val hostConnection: HostConnection,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : RequestRemoteDataSource {
    companion object {
        private const val HOST_ADDRESS = "http://192.168.0.215:8080/RoomyAppServer/"
        private const val URL_ALL_REQUESTS= "${HOST_ADDRESS}request/allRequests"
        private const val URL_UPDATE_REQUEST = "${HOST_ADDRESS}request/allRequests"
        private const val URL_DELETE_REQUEST = "${HOST_ADDRESS}request/deleteRequest/"
    }
    private val gson = GsonBuilder().create()
    override fun getRequest(): Request {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRequests(): List<Request> {
        val response = withContext(dispatcher) {
            hostConnection.sendGetRequest(URL_ALL_REQUESTS)
        }
        val listType: Type = object : TypeToken<List<Request>>() {}.type

        return if (response == "") listOf()
        else gson.fromJson(response, listType)
    }

    override suspend fun updateRequest(id: String, requestProperties: String): HostActionStatus {
        val response = withContext(dispatcher) {
            hostConnection.sendPut(URL_ALL_REQUESTS, requestProperties)
        }
        return if (response == "Success") HostActionStatus.SUCCESS
        else HostActionStatus.ERROR
    }

    override suspend fun removeRequest(requestId: String): HostActionStatus {
        val response = withContext(dispatcher) {
            hostConnection.sendDelete(URL_DELETE_REQUEST + requestId)
        }
        return if (response == "Success") HostActionStatus.SUCCESS
        else HostActionStatus.ERROR
    }
}