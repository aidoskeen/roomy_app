package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import com.aidos.roomy_app.data.DormitoryRepository
import com.aidos.roomy_app.data.RoomRepository
import com.aidos.roomy_app.data.UserRepository
import javax.inject.Inject

class MyRoomViewModel @Inject constructor(
    private val dormitoryRepository: DormitoryRepository,
    private val userRepository: UserRepository,
    private val roomRepository: RoomRepository
): ViewModel() {

}