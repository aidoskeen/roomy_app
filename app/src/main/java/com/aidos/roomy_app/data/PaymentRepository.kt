package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.MonthlyPayment

interface PaymentRepository {

    fun getPayments(): List<MonthlyPayment>

    fun getPayment(id: String): MonthlyPayment

    fun createPayment(payment: MonthlyPayment)

    fun updatePayment(id: String, updatedPayment: MonthlyPayment)

    fun deletePayment(id: String)

    fun refreshAllPayments()

    fun deleteAllPayments()
}