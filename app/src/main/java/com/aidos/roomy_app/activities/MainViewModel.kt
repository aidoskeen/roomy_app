package com.aidos.roomy_app.activities

import androidx.lifecycle.ViewModel
import com.aidos.roomy_app.data.repository.DefaultUserRepository
import com.aidos.roomy_app.data.repository.UserRepository
import com.aidos.roomy_app.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val currentResidentFlow: StateFlow<User.Resident> = userRepository.getCurrentUserFlow()


    fun getCurrentResident(): User.Resident = currentResidentFlow.value

}