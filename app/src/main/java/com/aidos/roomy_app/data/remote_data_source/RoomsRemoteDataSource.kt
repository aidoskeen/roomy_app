package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Room

interface RoomsRemoteDataSource {

    suspend fun getDormitoryRooms(dormitoryId: Int): List<Room>

    suspend fun getRoom(id: String): Room

    suspend fun createRoom(room: Room, dormitoryId: Int)

    suspend fun updateRoom(dormitoryId: Int, updatedRoom: Room)

    suspend fun deleteRoom(roomNumber: Int, dormitoryId: Int)

    suspend fun deleteAllRooms()
}