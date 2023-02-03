package com.aidos.roomy_app.models

data class Dormitory(
    val dormitoryId: Int,
    val address: String,
    val roomQuantity: Long,
    val rooms: List<Room>,
    val university: String
)
