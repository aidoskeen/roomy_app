package com.aidos.roomy_app.models

data class Dormitory(
    val dormitoryId: Int,
    val address: String = "",
    val rooms: List<Room> = listOf(),
    val university: String = "Vilnius Tech",
    val announcements: List<Announcement> = listOf()
) : java.io.Serializable
