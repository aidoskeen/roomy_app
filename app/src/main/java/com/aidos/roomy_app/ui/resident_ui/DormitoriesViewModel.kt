package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.DormitoryRepository
import com.aidos.roomy_app.models.Dormitory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
}