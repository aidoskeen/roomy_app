package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.MonthlyPayment

class DefaultPaymentRepository () : PaymentRepository {
    override fun getPayments(): List<MonthlyPayment> {
        TODO("Not yet implemented")
    }

    override fun getPayment(id: String): MonthlyPayment {
        TODO("Not yet implemented")
    }

    override fun createPayment(payment: MonthlyPayment) {
        TODO("Not yet implemented")
    }

    override fun updatePayment(id: String, updatedPayment: MonthlyPayment) {
        TODO("Not yet implemented")
    }

    override fun deletePayment(id: String) {
        TODO("Not yet implemented")
    }

    override fun refreshAllPayments() {
        TODO("Not yet implemented")
    }

    override fun deleteAllPayments() {
        TODO("Not yet implemented")
    }
}