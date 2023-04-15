package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.Room
import javax.inject.Inject

class RoomsRemoteData @Inject constructor(
    val hostConnection: HostConnection
) : RoomsRemoteDataSource {
    override fun getDormitoryRooms(dormitoryId: Int): List<Room> {
        TODO("Not yet implemented")
    }

    override fun getRoom(id: String): Room {
        TODO("Not yet implemented")
    }

    override fun createRoom(room: Room, dormitoryId: Int) {
        TODO("Not yet implemented")
    }

    override fun updateRoom(dormitoryId: Int, updatedRoom: Room) {
        TODO("Not yet implemented")
    }


    override fun deleteRoom(roomNumber: Int, dormitoryId: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteAllRooms() {
        TODO("Not yet implemented")
    }
}