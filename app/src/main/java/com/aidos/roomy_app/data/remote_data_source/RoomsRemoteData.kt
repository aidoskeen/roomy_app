package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Room

class RoomsRemoteData() : RoomsRemoteDataSource {
    override fun getRooms(): List<Room> {
        TODO("Not yet implemented")
    }

    override fun getRoom(id: String): MonthlyPayment {
        TODO("Not yet implemented")
    }

    override fun createRoom(room: Room) {
        TODO("Not yet implemented")
    }

    override fun updateRoom(id: String, updatedRoom: Room) {
        TODO("Not yet implemented")
    }

    override fun deleteRoom(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAllRooms() {
        TODO("Not yet implemented")
    }
}