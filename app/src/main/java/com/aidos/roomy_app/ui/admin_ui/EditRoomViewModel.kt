package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.RoomRepository
import com.aidos.roomy_app.models.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditRoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository
): ViewModel() {

    fun updateRoom(dormitoryId: Int, room: Room) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                roomRepository.updateRoom(dormitoryId, room)
            }
        }
    }
}