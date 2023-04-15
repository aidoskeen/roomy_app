package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.Dormitory
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject


class DormitoryRemoteData @Inject constructor(
    private val hostConnection: HostConnection
) : DormitoryRemoteDataSource {
    val gson = GsonBuilder().create()
    override fun getDormitories(): List<Dormitory> {
        val response = hostConnection.sendGetRequest(URL_ALL_DORMITORIES)
        val listType: Type = object : TypeToken<List<Dormitory>>() {}.type
        val dormitories: List<Dormitory> = gson.fromJson(response, listType)
        return if (response == "") listOf()
        else dormitories
    }

    override fun getDormitoryById(id: Int): Dormitory {
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
        private const val HOST_ADDRESS = "http://localhost:8080/RoomyAppServer/"
        const val URL_ALL_DORMITORIES = "${HOST_ADDRESS}dormitory/allDormitories"
    }
}