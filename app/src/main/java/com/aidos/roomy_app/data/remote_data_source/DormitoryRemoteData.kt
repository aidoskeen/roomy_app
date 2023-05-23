package com.aidos.roomy_app.data.remote_data_source

import android.util.Log
import com.aidos.roomy_app.data.DeserializationTools.DormitoryDeserializer
import com.aidos.roomy_app.data.ResponseModels.DormitoryResponse
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.frameworks.dagger.subcomponents.ApplicationScope
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.Announcement
import com.aidos.roomy_app.models.Dormitory
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.lang.reflect.Type
import javax.inject.Inject


class DormitoryRemoteData @Inject constructor(
    private val hostConnection: HostConnection,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : DormitoryRemoteDataSource {
    private val deserializer = DormitoryDeserializer().getDormitoryJsonDeserializer()
    private val gson = GsonBuilder()
        .registerTypeAdapter(Dormitory::class.java, deserializer)
        .create()

    override suspend fun getDormitories(): List<Dormitory> {
        val response = withContext(dispatcher) {
            hostConnection.sendGetRequest(URL_ALL_DORMITORIES)
        }
        val listType: Type = object : TypeToken<List<Dormitory>>() {}.type

        return if (response == "") listOf()
        else gson.fromJson(response, listType)
    }

    override suspend fun createAnnouncement(announcement: Announcement) : HostActionStatus {
        val announcementJson = gson.toJson(announcement)
        val response = withContext(dispatcher) {
            hostConnection.sendPost(URL_NEW_ANNOUNCEMENT, announcementJson.toString())
        }

        return if (response == "Error") {
            Log.e("DormitoryRemoteData", "Connection error")
            HostActionStatus.ERROR
        } else HostActionStatus.SUCCESS
    }

    override suspend fun getDormitoryById(id: Int): Dormitory {
        TODO("Not yet implemented")
    }

    override suspend fun saveDormitories(dormitories: List<Dormitory>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDormitory(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDormitory(id: String) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val HOST_ADDRESS = "http://192.168.0.215:8080/RoomyAppServer/"
        private const val URL_ALL_DORMITORIES = "${HOST_ADDRESS}dormitory/allDormitories"
        private const val URL_NEW_ANNOUNCEMENT = "${HOST_ADDRESS}dormitory/newAnnouncement"
    }
}