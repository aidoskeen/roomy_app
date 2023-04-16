package com.aidos.roomy_app.data.DeserializationTools

import com.aidos.roomy_app.enums.PaymentStatus
import com.aidos.roomy_app.models.MonthlyPayment
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PaymentDeserializer {

    fun getPaymentJsonDeserializer() : JsonDeserializer<MonthlyPayment> {
        val deserializer = JsonDeserializer {
                element: JsonElement,
                _: Type,
                _: JsonDeserializationContext ->
            val parser = GsonBuilder().create()
            val paymentJson = element.asJsonObject
            val id = paymentJson.get("paymentId").asInt
            val month = paymentJson.get("month").asString
            val dueDate = paymentJson.get("dueDate").asString
            val paymentStatus = when(paymentJson.get("paymentStatus").asString) {
                "NONE" -> PaymentStatus.NONE
                "PAID" -> PaymentStatus.PAID
                "OVERDUE" -> PaymentStatus.OVERDUE
                else -> PaymentStatus.NONE
            }

            MonthlyPayment(
                paymentId = id.toString(),
                month = month,
                dueDate = dueDate,
                paymentStatus = paymentStatus
            )
        }
        return deserializer
    }
}