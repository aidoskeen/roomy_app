package com.aidos.roomy_app.models

import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType

data class Room(
    val roomNumber: Int,
    val roomType: RoomType,
    val roomSize: RoomSize,
    val places: List<Place> = listOf(),
    val description: String = ""
)
