package com.aidos.roomy_app.ui.admin_ui

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.DefaultUserRepository
import com.aidos.roomy_app.data.repository.PlaceRepository
import com.aidos.roomy_app.data.repository.RequestRepository
import com.aidos.roomy_app.data.repository.UserRepository
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.enums.RequestStatus
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Request
import com.aidos.roomy_app.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestsViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _requestsStateFlow: MutableStateFlow<List<Place>> = MutableStateFlow(listOf())
    private val _messageStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val requestsStateFlow: StateFlow<List<Place>> = _requestsStateFlow
    val messageStateFlow: StateFlow<String> = _messageStateFlow

    fun loadRequests(dormitoryId: Int) {
        viewModelScope.launch {
            _requestsStateFlow.update {
                placeRepository.getAllPlacesInDormitory(dormitoryId)
            }
        }
    }

    fun setRequestStatus(place: Place, requestStatus: RequestStatus) {
        val updatedPlace = place.copy(requestStatus = requestStatus)
        val updatedResident = place.livingResident?.copy(roomNumber = place.roomNumber)
        viewModelScope.launch {
            val status = withContext(Dispatchers.Default) {
                userRepository.updateResident(updatedResident as User.Resident)
                placeRepository.updatePlace(updatedPlace)
            }
            if (status == HostActionStatus.SUCCESS){
                _messageStateFlow.update { status.toString() }
            }
        }
    }
}