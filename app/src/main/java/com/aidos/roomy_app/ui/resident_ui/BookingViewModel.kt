package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.R
import com.aidos.roomy_app.data.repository.DormitoryRepository
import com.aidos.roomy_app.data.repository.PlaceRepository
import com.aidos.roomy_app.data.repository.RoomRepository
import com.aidos.roomy_app.data.repository.UserRepository
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.ui.ui_components.MessageItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BookingUiState(
    val message: MessageItem = MessageItem(0),
    val showMessage: Boolean = false
)

class BookingViewModel @Inject constructor(
    val dormitoryRepository: DormitoryRepository,
    val placeRepository: PlaceRepository,
    val userRepository: UserRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    fun showMessage(message: MessageItem) {
        viewModelScope.launch {
            _uiState.value = BookingUiState(
                message.copy(textRes = message.textRes, iconRes = message.iconRes),
                showMessage = true
            )

            delay(1000L)

            _uiState.value = BookingUiState(showMessage = false)
        }


    }

    fun startBooking(place: Place) {
        viewModelScope.launch {
            val result = placeRepository.updatePlace(place)
            if (result == HostActionStatus.SUCCESS) {
                showMessage(MessageItem(R.string.request_sent_message))
            }
            else showMessage(MessageItem(R.string.error_message))
        }
    }
}