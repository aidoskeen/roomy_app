package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.models.Room

interface RoomRepository {

    suspend fun getRoomByNumber(roomNumber: Int): Room?
    suspend fun getDormitoryRooms(dormitoryId: Int): List<Room>

    suspend fun updateRoom(dormitoryId: Int, room: Room)

    suspend fun deleteRoom(roomNumber: Int, dormitoryId: Int)
}