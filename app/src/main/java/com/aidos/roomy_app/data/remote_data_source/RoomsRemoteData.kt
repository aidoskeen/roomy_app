package com.aidos.roomy_app.data.remote_data_source

import android.util.Log
import com.aidos.roomy_app.data.Constants.HOST_ADDRESS
import com.aidos.roomy_app.data.DeserializationTools.UserDeserializer
import com.aidos.roomy_app.data.ResponseModels.DormitoryResponse
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.models.User
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
    private val gson = GsonBuilder()
        .registerTypeAdapter(User.Resident::class.java, UserDeserializer().getResidentDeserializer())
        .create()
    override suspend fun getRoomByNumber(roomNumber: Int): Room? {
        val response = withContext(dispatcher) {
            hostConnection.sendGetRequest(URL_GET_ROOM_BY_NUMBER + "$roomNumber")
        }

        if (response == "")  return null
        else
            return try {
                gson.fromJson(response, Room::class.java)
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
    }

    override suspend fun getDormitoryRooms(dormitoryId: Int): List<Room> {
        val response = withContext(dispatcher) {
            hostConnection.sendGetRequest("$URL_GET_ROOMS_BY_DORMITORY/$dormitoryId")
        }
        val listType: Type = object : TypeToken<List<Room>>() {}.type

        if (response == "")  return listOf()

        else
            return try {
                gson.fromJson(response, listType)
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                listOf()
            }
    }

    override suspend fun getRoomByResident(residentId: Int) : Room? {
        val response = withContext(dispatcher) {
            hostConnection.sendGetRequest("$URL_GET_ROOM_BY_RESIDENT/$residentId")
        }

        return if (response == "") null
        else
            try {
                gson.fromJson(response, Room::class.java)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
    }


    override suspend fun updateRoom(updatedRoom: Room): HostActionStatus {
        val updateGson = GsonBuilder().create()
        val roomJson = updateGson.toJson(updatedRoom)
        val response = withContext(dispatcher) {
            hostConnection.sendPut(URL_UPDATE_ROOM_BY_DORMITORY, roomJson.toString())
        }
        return if (response == "ERROR") HostActionStatus.ERROR
        else HostActionStatus.SUCCESS

    }


    override suspend fun deleteRoom(roomNumber: Int, dormitoryId: Int) {
        TODO("Not yet implemented")
    }


    companion object {
        private const val URL_GET_ROOM_BY_RESIDENT = "${HOST_ADDRESS}room/byResident"
        private const val URL_GET_ROOMS_BY_DORMITORY = "${HOST_ADDRESS}room/allRooms"
        private const val URL_GET_ROOM_BY_NUMBER = "${HOST_ADDRESS}room/byNumber/"
        private const val URL_UPDATE_ROOM_BY_DORMITORY = "${HOST_ADDRESS}room/updateRoom/"
    }
}