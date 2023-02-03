package com.aidos.roomy_app.models

import java.time.LocalDate

data class Place(
    val available: Boolean = true,
    val price: Long,
    val livingResident: User.Resident? = null,
    val contractExpiryDate: LocalDate
)
