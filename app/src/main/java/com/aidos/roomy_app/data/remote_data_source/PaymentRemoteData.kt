package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.data.DeserializationTools.PaymentDeserializer
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.MonthlyPayment
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject

class PaymentRemoteData @Inject constructor(
    val hostConnection: HostConnection,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : PaymentsRemoteDataSource {
    private val deserializer = PaymentDeserializer().getPaymentJsonDeserializer()
    private val gson = GsonBuilder()
        .registerTypeAdapter(MonthlyPayment::class.java, deserializer)
        .create()

    companion object {
        private const val HOST_ADDRESS = "http://192.168.0.215:8080/RoomyAppServer/"
        private const val URL_CREATE = "${HOST_ADDRESS}dormitory/createPayments"
        private const val URL_GET_PAYMENTS = "${HOST_ADDRESS}payment/getAllPayments"
    }
    override suspend fun getPayments(dormitoryId: Int): List<MonthlyPayment> {
        val response = withContext(dispatcher) {
            hostConnection.sendGetRequest("$URL_GET_PAYMENTS/$dormitoryId")
        }
        val listType: Type = object : TypeToken<List<MonthlyPayment>>() {}.type

        return if (response == "") listOf()
        else gson.fromJson(response, listType)
    }

    override suspend fun getPayment(id: String): MonthlyPayment {
        TODO("Not yet implemented")
    }

    override suspend fun createPayments(dormitoryId: Int): HostActionStatus {
        val response = withContext(dispatcher) {
            hostConnection.sendPut("$URL_CREATE/$dormitoryId", "{}")
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