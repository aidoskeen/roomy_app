package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.RoomsRemoteDataSource
import com.aidos.roomy_app.models.Room
import javax.inject.Inject

class DefaultRoomRepository @Inject constructor(
    val roomsRemoteDataSource: RoomsRemoteDataSource
): RoomRepository {

    override suspend fun getDormitoryRooms(dormitoryId: Int): List<Room> = roomsRemoteDataSource.getDormitoryRooms(dormitoryId)

    override suspend fun addRoomToDormitory(room: Room, dormitoryId: Int)  = roomsRemoteDataSource.createRoom(room, dormitoryId)

    override suspend fun updateRoom(dormitoryId: Int, room: Room) = roomsRemoteDataSource.updateRoom(dormitoryId, room)

    override suspend fun deleteRoom(roomNumber: Int, dormitoryId: Int) = roomsRemoteDataSource.deleteRoom(roomNumber, dormitoryId)
}