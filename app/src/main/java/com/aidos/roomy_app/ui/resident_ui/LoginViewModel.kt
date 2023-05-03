package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel() {

    fun logIn(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUserByLoginData(username, password)
            if (user == null) {
                //TODO
            }
        }
    }
}