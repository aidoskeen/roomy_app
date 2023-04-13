package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.MonthlyPayment

interface PaymentRepository {

    suspend fun getPayments(): List<MonthlyPayment>

    suspend fun getPayment(id: String): MonthlyPayment

    suspend fun createPayment(payment: MonthlyPayment)

    suspend fun updatePayment(id: String, updatedPayment: MonthlyPayment)

    suspend fun deletePayment(id: String)

    fun refreshAllPayments()

    suspend fun deleteAllPayments()
}