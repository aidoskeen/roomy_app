package com.aidos.roomy_app.models

data class Dormitory(
    val dormitoryId: Int,
    val address: String = "",
    val rooms: List<Room> = listOf(),
    val university: String = "Vilnius Tech",
    val administrator: User.Administrator = User.Administrator(0)
) : java.io.Serializable
