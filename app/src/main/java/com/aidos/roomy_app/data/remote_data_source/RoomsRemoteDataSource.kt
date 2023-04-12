package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Room

interface RoomsRemoteDataSource {

    fun getRooms(): List<Room>

    fun getRoom(id: String): MonthlyPayment

    fun createRoom(room: Room)

    fun updateRoom(id: String, updatedRoom: Room)

    fun deleteRoom(id: String)

    fun deleteAllRooms()
}