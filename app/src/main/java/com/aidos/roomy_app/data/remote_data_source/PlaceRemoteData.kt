package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.Place
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaceRemoteData @Inject constructor(
    private val hostConnection: HostConnection,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
): PlaceRemoteDataSource {
    val gson = GsonBuilder().create()
    override suspend fun updatePlace(placeId: String, properties: String): HostActionStatus {
        val response = withContext(dispatcher) {
            hostConnection.sendPut(URL_UPDATE + "/$placeId", properties)
        }
        return if (response == "Success") HostActionStatus.SUCCESS
        else HostActionStatus.ERROR
    }

    override suspend fun getPlace(placeId: Int): Place? {
        val response = withContext(dispatcher) {
            hostConnection.sendGetRequest(URL_GET + "/$placeId")
        }
        return if (response != "") gson.fromJson(response, Place::class.java)
        else null
    }


    companion object {
        private const val HOST_ADDRESS = "http://192.168.0.215:8080/RoomyAppServer/"
        private const val URL_UPDATE = "${HOST_ADDRESS}place/updatePlace"
        private const val URL_GET = "${HOST_ADDRESS}place/getPlace"
    }
}