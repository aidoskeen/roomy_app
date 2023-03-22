package com.aidos.roomy_app.models

import com.aidos.roomy_app.enums.PaymentStatus

data class MonthlyPayment(
    val month: String = "",
    val date: String = "",
    val paymentStatus: PaymentStatus = PaymentStatus.NONE,
)
