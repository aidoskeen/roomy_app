package com.aidos.roomy_app.models

import com.aidos.roomy_app.enums.PaymentStatus

data class MonthlyPayment(
    val paymentId: String,
    val month: String = "",
    val dueDate: String = "",
    val paymentStatus: PaymentStatus = PaymentStatus.NONE,
) : java.io.Serializable
