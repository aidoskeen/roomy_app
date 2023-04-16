package com.aidos.roomy_app.data.DeserializationTools

import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.User
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PlaceDeserializer {

    fun getPlaceJsonDeserializer() : JsonDeserializer<Place> {
        val deserializer = JsonDeserializer {
                element: JsonElement,
                _: Type,
                _: JsonDeserializationContext ->
            val residentDeserializer = UserDeserializer()
            val paymentDeserializer = PaymentDeserializer()
            val parser = GsonBuilder()
                .registerTypeAdapter(User.Resident::class.java, residentDeserializer.getResidentDeserializer())
                .registerTypeAdapter(MonthlyPayment::class.java, paymentDeserializer.getPaymentJsonDeserializer())
                .create()
            val placeJson = element.asJsonObject
            val id = placeJson.get("placeId").asInt
            val available = placeJson.get("available").asBoolean
            val price = placeJson.get("price").asLong
            val livingResident: User.Resident? = parser.fromJson(
                placeJson.getAsJsonObject("livingResident"),
                User.Resident::class.java
            )
            val monthlyPayment = parser.fromJson(
                placeJson.getAsJsonObject("monthlyPayment"),
                MonthlyPayment::class.java
            )

            Place(
                livingResident = livingResident,
                placeId = id,
                price = price,
                available = available,
                monthlyPayment = monthlyPayment
            )
        }
        return deserializer
    }

    fun getPlaceListJsonDeserializer(): JsonDeserializer<List<Place>> {
        val deserializer = JsonDeserializer {
                element: JsonElement,
                _: Type,
                _: JsonDeserializationContext ->
            val array = element.asJsonArray
            val parser = GsonBuilder()
                .registerTypeAdapter(Place::class.java, getPlaceJsonDeserializer())
                .create()
            array.map {
                parser.fromJson(it, Place::class.java)
            }
        }
        return deserializer
    }
}