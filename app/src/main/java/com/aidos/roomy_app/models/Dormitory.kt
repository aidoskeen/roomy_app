package com.aidos.roomy_app.models

data class Dormitory(
    val dormitoryId: Int,
    val address: String = "",
    val roomQuantity: Long = 0,
    val rooms: List<Room> = listOf(),
    val university: String = "Vilnius Tech"
)
