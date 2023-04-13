package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.Room

interface RoomRepository {

    suspend fun getDormitoryRooms(dormitoryId: Int): List<Room>

    suspend fun addRoomToDormitory(room: Room, dormitoryId: Int)

    suspend fun updateRoom(dormitoryId: Int, room: Room)

    suspend fun deleteRoom(roomNumber: Int, dormitoryId: Int)
}