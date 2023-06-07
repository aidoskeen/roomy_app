package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.DormitoryRepository
import com.aidos.roomy_app.models.Announcement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MakeAnnouncementViewModel @Inject constructor(
    private val dormitoryRepository: DormitoryRepository
): ViewModel() {
    val onSuccess = MutableLiveData<Boolean>()
    fun makeAnnouncement(announcement: Announcement) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                dormitoryRepository.createAnnouncement(announcement)
            }
        }
    }

}