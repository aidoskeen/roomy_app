package com.aidos.roomy_app.data.DeserializationTools
import com.aidos.roomy_app.models.Announcement
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Room
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DormitoryDeserializer() {

    fun getDormitoryJsonDeserializer() : JsonDeserializer<Dormitory> {
        val deserializer = JsonDeserializer {
                element: JsonElement,
                _: Type,
                _: JsonDeserializationContext ->
            val roomsDeserializer = RoomDeserializer().getRoomListJsonDeserializer()
            val typeRoom = object : TypeToken<List<Room>>() {}.type
            val typeAnnouncement = object : TypeToken<List<Announcement>>() {}.type
            val parser = GsonBuilder()
                .registerTypeAdapter(typeRoom, roomsDeserializer)
                .create()

            val dormitoryJson = element.asJsonObject
            val id = dormitoryJson.get("dormitoryId").asInt
            val address = dormitoryJson.get("address").asString
            val rooms = parser.fromJson<List<Room>>(dormitoryJson.get("rooms").asJsonArray, typeRoom)
            val announcements = parser.fromJson<List<Announcement>>(dormitoryJson.get("announcements").asJsonArray, typeAnnouncement)
            val university = dormitoryJson.get("university").asString

            Dormitory(
                dormitoryId = id,
                address = address,
                rooms = rooms,
                university = university,
                announcements = announcements ?: listOf()
            )
        }
        return deserializer
    }

    fun getListJsonDeserializer(): JsonDeserializer<List<Dormitory>> {
        val deserializer = JsonDeserializer {
                element: JsonElement,
                _: Type,
                _: JsonDeserializationContext ->
            val array = element.asJsonArray
            val parser = GsonBuilder()
                .registerTypeAdapter(Dormitory::class.java, getDormitoryJsonDeserializer())
                .create()
            array.map {
                parser.fromJson(it, Dormitory::class.java)
            }
        }
        return deserializer
    }

}