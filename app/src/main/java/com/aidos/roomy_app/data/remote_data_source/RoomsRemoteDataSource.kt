package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Room

interface RoomsRemoteDataSource {
    suspend fun getRoomByNumber(roomNumber: Int): Room?
    suspend fun getDormitoryRooms(dormitoryId: Int): List<Room>

    suspend fun updateRoom(dormitoryId: Int, updatedRoom: Room)

    suspend fun deleteRoom(roomNumber: Int, dormitoryId: Int)

}