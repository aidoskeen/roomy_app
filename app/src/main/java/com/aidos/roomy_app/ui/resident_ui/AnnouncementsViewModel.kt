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
            "Renovation","Rooms will be renovated"
            ),
            Announcement(2,
            "Changes in rules",
                "From may 1st, rules will be changed"
            )
        )
        return Dormitory(1, announcements = annoucements)
    }


}