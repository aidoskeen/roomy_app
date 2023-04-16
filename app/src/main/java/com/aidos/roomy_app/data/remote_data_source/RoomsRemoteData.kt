package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.data.ResponseModels.DormitoryResponse
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject

class RoomsRemoteData @Inject constructor(
    val hostConnection: HostConnection,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : RoomsRemoteDataSource {
    private val gson = GsonBuilder().create()
    override suspend fun getDormitoryRooms(dormitoryId: Int): List<Room> {
        val response = withContext(dispatcher) {
            hostConnection.sendGetRequest(DormitoryRemoteData.URL_ALL_DORMITORIES)
        }
        val listType: Type = object : TypeToken<List<DormitoryResponse>>() {}.type

        return if (response == "") listOf()
        else gson.fromJson(response, listType)
    }

    override suspend fun getRoom(id: String): Room {
        TODO("Not yet implemented")
    }

    override suspend fun createRoom(room: Room, dormitoryId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRoom(dormitoryId: Int, updatedRoom: Room) {
        TODO("Not yet implemented")
    }


    override suspend fun deleteRoom(roomNumber: Int, dormitoryId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllRooms() {
        TODO("Not yet implemented")
    }
}