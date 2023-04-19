package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.data.remote_data_source.PlaceRemoteDataSource
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Place
import com.google.gson.GsonBuilder
import javax.inject.Inject

class DefaultPlaceRepository @Inject constructor(
    private val dataSource: PlaceRemoteDataSource,
) : PlaceRepository {
    override suspend fun updatePlace(place: Place): HostActionStatus {
        val gson = GsonBuilder().create()
        val properties = "{\"resident\":${gson.toJson(place.livingResident)}}"
        val result = dataSource.updatePlace(place.placeId.toString(), properties)
        return result
    }
}