package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import com.aidos.roomy_app.data.repository.UserRepository
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

}