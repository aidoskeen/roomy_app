package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import com.aidos.roomy_app.models.Announcement
import com.aidos.roomy_app.models.Dormitory
import javax.inject.Inject

class AnnouncementsViewModel @Inject constructor(

): ViewModel() {
    fun generateFakeDormitory(): Dormitory {
        val annoucements = listOf(
            Announcement(1,
            "Update on big rooms", "From may 1st, 3 people will live in big rooms"
            ),
            Announcement(2,
            "Price change",
            "The price will be increased by 50 euros"
            ),
            Announcement(3,
            "Renovation", "Floors from 3 to 10 will be renovated"
            )
        )
        return Dormitory(1, announcements = annoucements)
    }
}