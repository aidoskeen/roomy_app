package com.aidos.roomy_app.data.remote_data_source

import com.aidos.roomy_app.models.User
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    val hostConnection: HostConnection
) : UserRemoteDataSource {
    override suspend fun fetchUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUsers(users: List<User>) {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun removeUser(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByLoginData(login: String, password: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun updateResident(id: Int, resident: User.Resident) {
        TODO("Not yet implemented")
    }

}