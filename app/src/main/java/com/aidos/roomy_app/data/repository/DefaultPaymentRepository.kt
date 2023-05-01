package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.data.remote_data_source.PaymentsRemoteDataSource
import com.aidos.roomy_app.models.MonthlyPayment

class DefaultPaymentRepository (
    private val paymentsDataSource: PaymentsRemoteDataSource
        ) : PaymentRepository {
    override suspend fun getPayments(): List<MonthlyPayment> = paymentsDataSource.getPayments()

    override suspend fun getPayment(id: String): MonthlyPayment = paymentsDataSource.getPayment(id)
    override suspend fun createPayments(dormitoryId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePayment(id: String, updatedPayment: MonthlyPayment) = paymentsDataSource.updatePayment(id, updatedPayment)

    override suspend fun deletePayment(id: String) = paymentsDataSource.deletePayment(id)

    override fun refreshAllPayments() { }

    override suspend fun deleteAllPayments() = paymentsDataSource.deleteAllPayments()
}