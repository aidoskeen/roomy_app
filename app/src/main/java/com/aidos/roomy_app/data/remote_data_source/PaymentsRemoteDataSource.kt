package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.MonthlyPayment

interface PaymentsRemoteDataSource {

    fun getPayments(): List<MonthlyPayment>

    fun getPayment(id: String): MonthlyPayment

    fun createPayment(payment: MonthlyPayment)

    fun updatePayment(id: String, updatedPayment: MonthlyPayment)

    fun deletePayment(id: String)

    fun deleteAllPayments()
}