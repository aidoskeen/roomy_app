package com.aidos.roomy_app.models


data class Place(
    val available: Boolean = true,
    val price: Long,
    val livingResident: User.Resident? = null,
    val monthlyPayment: MonthlyPayment = MonthlyPayment()
)
