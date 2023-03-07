package com.aidos.roomy_app.models

import com.aidos.roomy_app.enums.RequestStatus

data class Request(
    val requestId: Int,
    val requester: User.Resident,
    val room: Room,
    val dormitory: Dormitory,
    val requestStatus: RequestStatus
)
