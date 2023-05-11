package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.DormitoryRepository
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DormitoriesViewModel  @Inject constructor(
    private val dormitoryRepository: DormitoryRepository
): ViewModel() {
    private val _dormitoriesStateFlow = MutableStateFlow<List<Dormitory>>(listOf())
    val dormitoriesStateFlow = _dormitoriesStateFlow
    fun loadDormitories() {
       viewModelScope.launch {
           _dormitoriesStateFlow.update {
               dormitoryRepository.getDormitories()
           }
       }
    }

    fun createFakeDormitories(): List<Dormitory> {
        return listOf(
            Dormitory(
                dormitoryId = 11,
                address = "Sauletekio 25",
                rooms = generateFakeRooms(),
                "VGTU"
            ),
            Dormitory(
                dormitoryId = 11,
                address = "Sauletekio 25",
                rooms = generateFakeRooms(),
                "VGTU"
            )
        )
    }

    private fun generateFakeRooms(): List<Room> {
        val roomList = mutableListOf<Room>()
        for (i in 0..20) {
            val room = Room(
                roomNumber = i + 100,
                roomSize = RoomSize.SMALL,
                roomType = RoomType.DOUBLE,
                places = generateFakePlaces()
            )
            roomList.add(room)
        }
        return roomList
    }

    private fun generateFakePlaces(): List<Place> {
        return mutableListOf(
            Place(price = 100L),
            Place(price = 100L)
        )
    }
}