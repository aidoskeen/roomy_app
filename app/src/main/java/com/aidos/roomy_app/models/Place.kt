package com.aidos.roomy_app.models

import com.aidos.roomy_app.enums.RequestStatus


data class Place(
    val placeId: Int? = null,
    val available: Boolean = true,
    val price: Long,
    val livingResident: User.Resident? = null,
    val monthlyPayment: MonthlyPayment? = MonthlyPayment(""),
    val requestStatus: RequestStatus = RequestStatus.NONE,
    val roomNumber: Int? = null
) : java.io.Serializable {
}
