package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.DormitoryRepository
import com.aidos.roomy_app.data.repository.PlaceRepository
import com.aidos.roomy_app.data.repository.RoomRepository
import com.aidos.roomy_app.data.repository.UserRepository
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BookingUiState(
    val title: String = "",
    val description: String = "",
    val isTaskCompleted: Boolean = false,
    val isLoading: Boolean = false,
    val userMessage: Int? = null,
    val isTaskSaved: Boolean = false
)

class BookingViewModel @Inject constructor(
    val dormitoryRepository: DormitoryRepository,
    val placeRepository: PlaceRepository,
    val userRepository: UserRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    fun startBooking(place: Place) {
        viewModelScope.launch {
            val result = placeRepository.updatePlace(place)
            if (result == HostActionStatus.SUCCESS) {
                //TODO
            }
        }
    }
}