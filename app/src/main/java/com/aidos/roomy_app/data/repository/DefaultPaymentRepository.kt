package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.data.remote_data_source.PaymentsRemoteDataSource
import com.aidos.roomy_app.models.MonthlyPayment
import javax.inject.Inject

class DefaultPaymentRepository @Inject constructor(
    private val paymentsDataSource: PaymentsRemoteDataSource
        ) : PaymentRepository {
    override suspend fun getPayments(dormitoryId: Int): List<MonthlyPayment> = paymentsDataSource.getPayments(dormitoryId)

    override suspend fun getPayment(id: String): MonthlyPayment = paymentsDataSource.getPayment(id)
    override suspend fun createPayments(dormitoryId: Int) = paymentsDataSource.createPayments(dormitoryId)

    override suspend fun updatePayment(id: String, updatedPayment: MonthlyPayment) = paymentsDataSource.updatePayment(id, updatedPayment)

    override suspend fun deletePayment(id: String) = paymentsDataSource.deletePayment(id)

    override fun refreshAllPayments() { }

    override suspend fun deleteAllPayments() = paymentsDataSource.deleteAllPayments()
}