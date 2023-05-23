package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.data.remote_data_source.RoomsRemoteDataSource
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Room
import javax.inject.Inject

class DefaultRoomRepository @Inject constructor(
    val roomsRemoteDataSource: RoomsRemoteDataSource
): RoomRepository {
    override suspend fun getRoomByNumber(roomNumber: Int): Room? = roomsRemoteDataSource.getRoomByNumber(roomNumber)

    override suspend fun getDormitoryRooms(dormitoryId: Int): List<Room> = roomsRemoteDataSource.getDormitoryRooms(dormitoryId)

    override suspend fun updateRoom(room: Room): HostActionStatus = roomsRemoteDataSource.updateRoom(room)

    override suspend fun deleteRoom(roomNumber: Int, dormitoryId: Int) = roomsRemoteDataSource.deleteRoom(roomNumber, dormitoryId)
}