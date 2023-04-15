package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.DormitoryRepository
import com.aidos.roomy_app.models.Dormitory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DormitoriesViewModel  @Inject constructor(
    private val dormitoryRepository: DormitoryRepository
): ViewModel() {

    fun getDormitories() : List<Dormitory> {
        var dormitories: List<Dormitory> = listOf()
        viewModelScope.launch {
            dormitories = dormitoryRepository.getDormitories()
        }
        return dormitories
    }
}