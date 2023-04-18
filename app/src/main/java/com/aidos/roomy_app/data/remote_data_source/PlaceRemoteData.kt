package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class PlaceRemoteData(
    private val hostConnection: HostConnection,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
): PlaceRemoteDataSource {

    override suspend fun updatePlace(place: Place, properties: String): HostActionStatus {
        val response = withContext(dispatcher) {
            hostConnection.sendPut(URL_UPDATE, properties)
        }
        return if (response == "Success") HostActionStatus.SUCCESS
        else HostActionStatus.ERROR
    }

    companion object {
        private const val HOST_ADDRESS = "http://192.168.0.215:8080/RoomyAppServer/"
        private const val URL_UPDATE = "${HOST_ADDRESS}place/updatePlace"
    }
}