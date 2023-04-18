package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Place

interface PlaceRemoteDataSource {

    suspend fun updatePlace(place: Place, properties: String): HostActionStatus
}