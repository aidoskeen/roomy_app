package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.User

interface UserDataSource {

    suspend fun loadUser(): List<User>

    suspend fun saveUsers(users: List<User>)
}