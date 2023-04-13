package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Room

interface RoomsRemoteDataSource {

    fun getDormitoryRooms(dormitoryId: Int): List<Room>

    fun getRoom(id: String): Room

    fun createRoom(room: Room, dormitoryId: Int)

    fun updateRoom(dormitoryId: Int, updatedRoom: Room)

    fun deleteRoom(roomNumber: Int, dormitoryId: Int)

    fun deleteAllRooms()
}