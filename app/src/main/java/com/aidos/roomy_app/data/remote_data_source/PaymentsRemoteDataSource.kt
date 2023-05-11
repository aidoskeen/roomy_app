package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.MonthlyPayment

interface PaymentsRemoteDataSource {

  suspend fun getPayments(dormitoryId: Int): List<MonthlyPayment>

  suspend fun getPayment(id: String): MonthlyPayment

  suspend fun createPayments(dormitoryId: Int): HostActionStatus

  suspend fun updatePayment(id: String, updatedPayment: MonthlyPayment)

  suspend fun deletePayment(id: String)

  suspend fun deleteAllPayments()
}