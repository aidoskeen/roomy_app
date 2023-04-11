package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import com.aidos.roomy_app.data.DormitoryRepository
import javax.inject.Inject

class MyRoomViewModel @Inject constructor(
    private val dormitoryRepository: DormitoryRepository
): ViewModel() {

}