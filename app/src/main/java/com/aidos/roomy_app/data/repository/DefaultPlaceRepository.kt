package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.data.remote_data_source.PlaceRemoteDataSource
import com.aidos.roomy_app.data.remote_data_source.RoomsRemoteDataSource
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.enums.RequestStatus
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Place
import com.google.gson.GsonBuilder
import javax.inject.Inject

class DefaultPlaceRepository @Inject constructor(
    private val dataSource: PlaceRemoteDataSource,
    private val roomRemoteDataSource: RoomsRemoteDataSource
) : PlaceRepository {
    override suspend fun updatePlace(place: Place): HostActionStatus {
        val gson = GsonBuilder().create()
        val resident = if (place.livingResident != null)
            "{\"resident\":${gson.toJson(place.livingResident)}"
        else "{"
        val properties = resident +
                "\"available\":\"${place.available}\"," +
                "\"requestStatus\":\"${place.requestStatus}\" }"
        val result = dataSource.updatePlace(place.placeId.toString(), properties)
        return result
    }

    override suspend fun getAllPlacesInDormitory(dormitoryId: Int): List<Place> {
        val rooms = roomRemoteDataSource.getDormitoryRooms(dormitoryId = dormitoryId)
        val allPlaces = mutableListOf<Place>()
        rooms.forEach {
            allPlaces.addAll(it.places)
        }
        allPlaces.filter { it.requestStatus != RequestStatus.NONE }

        return allPlaces
    }
}