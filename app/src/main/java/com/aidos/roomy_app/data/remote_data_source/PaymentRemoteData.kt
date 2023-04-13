package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.MonthlyPayment
import javax.inject.Inject

class PaymentRemoteData @Inject constructor(

) : PaymentsRemoteDataSource {
    override suspend fun getPayments(): List<MonthlyPayment> {
        TODO("Not yet implemented")
    }

    override suspend fun getPayment(id: String): MonthlyPayment {
        TODO("Not yet implemented")
    }

    override suspend fun createPayment(payment: MonthlyPayment) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePayment(id: String, updatedPayment: MonthlyPayment) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePayment(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllPayments() {
        TODO("Not yet implemented")
    }
}