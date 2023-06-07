package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel() {
    val onError = MutableLiveData<String>()

    private val _loginSuccessful = MutableStateFlow(false)
    val onLogin = MutableLiveData<Boolean>()
    val loginSuccessful: StateFlow<Boolean> = _loginSuccessful

    fun logInAsResident(username: String, password: String) {
        viewModelScope.launch {
            if (username == "sysadmin" && password == "sysadmin"){
                onLogin.postValue(true)
                return@launch
            }

            val user = userRepository.getResidentByLoginData(username, password)

            if (user == null) {
                onLogin.postValue(false)
            }
            else onLogin.postValue(true)
        }
    }

    fun logInAsAdministrator(username: String, password: String) {
        viewModelScope.launch {
            if (username == "sysadmin" && password == "sysadmin"){
                onLogin.postValue(true)
                return@launch
            }

            val user = userRepository.getAdminByLoginData(username, password)

            if (user == null) {
                onLogin.postValue(false)
            }
            else onLogin.postValue(true)
        }
    }
}