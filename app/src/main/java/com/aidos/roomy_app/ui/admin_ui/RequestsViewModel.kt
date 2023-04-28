package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.PlaceRepository
import com.aidos.roomy_app.data.repository.RequestRepository
import com.aidos.roomy_app.enums.RequestStatus
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestsViewModel @Inject constructor(
    private val placeRepository: PlaceRepository
): ViewModel() {
    private val _requestsStateFlow: MutableStateFlow<List<Place>> = MutableStateFlow(listOf())
    val requestsStateFlow = _requestsStateFlow
    fun loadRequests(dormitoryId: Int) {
        viewModelScope.launch {
            _requestsStateFlow.update {
                placeRepository.getAllPlacesInDormitory(dormitoryId)
            }
        }
    }

    fun setRequestStatus(place: Place, requestStatus: RequestStatus) {
        val updatedPlace = place.copy(requestStatus = requestStatus)
        viewModelScope.launch {
            val status = withContext(Dispatchers.Default) {
                placeRepository.updatePlace(updatedPlace)
            }
            //TODO: status handling and showing message
        }
    }
}