package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Place

interface PlaceRepository {

    suspend fun updatePlace(place: Place): HostActionStatus

    suspend fun getPlace(id: Int) : Place
    suspend fun getAllPlacesInDormitory(dormitoryId: Int): List<Place>

}