package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.User

interface UserRemoteDataSource {

    suspend fun fetchUsers(): List<User>

    suspend fun saveUsers(users: List<User>)

    suspend fun createUser(user: User): HostActionStatus

    suspend fun removeUser(userId: String)

    suspend fun getUserByLoginData(login: String, password: String): User?

    suspend fun updateResident(resident: User.Resident, properties: String)
}