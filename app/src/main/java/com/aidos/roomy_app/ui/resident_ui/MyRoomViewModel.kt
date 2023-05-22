package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.DormitoryRepository
import com.aidos.roomy_app.data.repository.RoomRepository
import com.aidos.roomy_app.data.repository.UserRepository
import com.aidos.roomy_app.enums.PaymentStatus
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.models.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MyRoomState(
    val room: Room,
    val place: Place
)
class MyRoomViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val roomRepository: RoomRepository
): ViewModel() {
    private val resident: User.Resident = userRepository.getCurrentUserFlow().value
    private val _placeFlow = MutableStateFlow(Place(price = 150, monthlyPayment = generateFakePayment()))
    private val _roomFlow = MutableStateFlow(generateFakeRoom())

    private val _viewState = combine(
        _placeFlow,
        _roomFlow
    ) { place, room ->
        MyRoomState(room, place)
    }

    val myRoomState = _viewState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        MyRoomState(generateFakeRoom(), Place(price = 150, monthlyPayment = generateFakePayment()))
    )

    fun loadData() {
        viewModelScope.launch {
            if (resident.id != -1) {
                val room = resident.roomNumber?.let { roomRepository.getRoomByNumber(it) }
                val place = room?.places?.find { it.livingResident == resident }
                if (place != null) {
                    _placeFlow.value = place
                }
                if (room != null) {
                    _roomFlow.value = room
                }
            }
        }
    }

    private fun generateFakePayment() = MonthlyPayment(paymentId = "1",month = "MAY", paymentStatus = PaymentStatus.NONE, dueDate = "2023/06/08")
    private fun generateFakeRoom(): Room {
        val place = Place(price = 100L, monthlyPayment = generateFakePayment())
        val room = Room(17, RoomType.DOUBLE, RoomSize.MEDIUM, listOf(place))
        return room
    }
}