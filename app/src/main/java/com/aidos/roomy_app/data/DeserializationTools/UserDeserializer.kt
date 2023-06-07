package com.aidos.roomy_app.data.DeserializationTools

import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.User
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class UserDeserializer() {

    fun getResidentDeserializer(): JsonDeserializer<User.Resident> {
        val deserializer = JsonDeserializer { element: JsonElement,
                                              _: Type,
                                              _: JsonDeserializationContext ->
            val residentJson = element.asJsonObject
            val id = residentJson.get("userId")?.asInt
            val name = residentJson.get("name").asString
            val surname = residentJson.get("surname").asString
            val username = residentJson.get("username").asString
            val password = residentJson.get("password").asString
            val roomNumber = residentJson.get("roomNumber")?.asInt

            User.Resident(
                id = id,
                name = name,
                surname = surname,
                username = username,
                password = password,
                roomNumber = roomNumber
            )
        }
        return deserializer
    }

    fun getAdminDeserializer(): JsonDeserializer<User.Administrator> {
        val deserializer = JsonDeserializer { element: JsonElement,
                                              _: Type,
                                              _: JsonDeserializationContext ->

            val parser = GsonBuilder()
                .registerTypeAdapter(Dormitory::class.java, DormitoryDeserializer().getDormitoryJsonDeserializer())
                .create()
            val adminJson = element.asJsonObject
            val id = adminJson.get("userId")?.asInt
            val name = adminJson.get("name").asString
            val surname = adminJson.get("surname").asString
            val username = adminJson.get("username").asString
            val password = adminJson.get("password").asString
            val dormitory = try {
                parser.fromJson(adminJson.getAsJsonObject("dormitory"), Dormitory::class.java)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }

            User.Administrator(
                id = id,
                name = name,
                surname = surname,
                username = username,
                password = password,
                dormitory = dormitory
            )
        }
        return deserializer
    }
}