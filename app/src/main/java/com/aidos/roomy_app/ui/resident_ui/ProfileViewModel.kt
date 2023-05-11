package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import com.aidos.roomy_app.data.repository.UserRepository
import com.aidos.roomy_app.models.User
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    fun generateFakeResident() = User.Resident(-1, "Aidos","Alimkhan", username = "aidos123")
}