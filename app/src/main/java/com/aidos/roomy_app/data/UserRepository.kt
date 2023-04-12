package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.User

interface UserRepository {

    suspend fun getAllUsers(): List<User>

    suspend fun getResidents(): List<User.Resident>

    suspend fun getAdmins(): List<User.Administrator>

    suspend fun createUser(user: User)

    suspend fun deleteUser(id: String)

    suspend fun getUserByLoginData(login: String, password: String): User

    fun updateResident(id: String, resident: User.Resident)

}