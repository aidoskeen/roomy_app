package com.aidos.roomy_app.models

data class Announcement(
    val announcementId: Int,
    val title: String,
    val text: String = ""
)
