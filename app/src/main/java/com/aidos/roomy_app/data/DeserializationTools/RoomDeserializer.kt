package com.aidos.roomy_app.data.DeserializationTools

import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class RoomDeserializer() {

    fun getRoomJsonDeserializer() : JsonDeserializer<Room> {
        val deserializer = JsonDeserializer {
                element: JsonElement,
                _: Type,
                _: JsonDeserializationContext ->
            val placesJsonDeserializer = PlaceDeserializer()
            val type = object : TypeToken<List<Place>>() {}.type
            val parser = GsonBuilder()
                .registerTypeAdapter(type, placesJsonDeserializer.getPlaceListJsonDeserializer())
                .create()

            val roomJson = element.asJsonObject
            val id = roomJson.get("roomNumber").asInt
            val roomSizeString = roomJson.get("roomSize").asString
            val roomTypeString = roomJson.get("roomType").asString
            val description = roomJson.get("description").asString
            val places = parser.fromJson<List<Place>>(roomJson.get("places").asJsonArray, type)
            val roomType = when(roomTypeString) {
                "DOUBLE" -> RoomType.DOUBLE
                "TRIPLE" -> RoomType.TRIPLE
                "SINGLE" -> RoomType.SINGLE
                else -> RoomType.SINGLE
            }

            val roomSize = when(roomSizeString) {
                "SMALL" -> RoomSize.SMALL
                "MEDIUM" -> RoomSize.MEDIUM
                "BIG" -> RoomSize.BIG
                else -> RoomSize.SMALL
            }
            Room(
                roomNumber = id,
                roomSize = roomSize,
                roomType = roomType,
                places = places,
                description = description
            )
        }
        return deserializer
    }

    fun getRoomListJsonDeserializer(): JsonDeserializer<List<Room>> {
        val deserializer = JsonDeserializer {
                element: JsonElement,
                _: Type,
                _: JsonDeserializationContext ->
            val array = element.asJsonArray
            val parser = GsonBuilder()
                .registerTypeAdapter(Room::class.java, getRoomJsonDeserializer())
                .create()
            array.map {
                parser.fromJson(it, Room::class.java)
            }
        }
        return deserializer
    }
}