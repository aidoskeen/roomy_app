package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.MonthlyPayment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentRemoteData @Inject constructor(
    val hostConnection: HostConnection,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : PaymentsRemoteDataSource {

    companion object {
        private const val HOST_ADDRESS = "http://192.168.0.215:8080/RoomyAppServer/"
        private const val URL_UPDATE = "${HOST_ADDRESS}dormitory/createPayments"
    }
    override suspend fun getPayments(): List<MonthlyPayment> {
        TODO("Not yet implemented")
    }

    override suspend fun getPayment(id: String): MonthlyPayment {
        TODO("Not yet implemented")
    }

    override suspend fun createPayments(dormitoryId: Int): HostActionStatus {
        val response = withContext(dispatcher) {
            hostConnection.sendPut("$URL_UPDATE/$dormitoryId", "{}")
        }
        return if (response == "Success") HostActionStatus.SUCCESS
        else HostActionStatus.ERROR
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