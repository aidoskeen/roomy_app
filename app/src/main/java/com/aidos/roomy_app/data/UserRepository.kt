package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.User

interface UserRepository {

    fun getAllUsers(): List<User>

    fun getResidents(): List<User.Resident>

    fun getAdmins(): List<User.Administrator>

    fun createUser(user: User)

    fun deleteUser(id: String)

    fun getUser(id: String): User

    fun updateResident(id: String, resident: User.Resident)

}