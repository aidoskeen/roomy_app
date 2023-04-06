package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.UserRemoteDataSource
import com.aidos.roomy_app.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUserRepository @Inject constructor(
    private val dataSource: UserRemoteDataSource
) : UserRepository {
    override fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override fun getResidents(): List<User.Resident> {
        TODO("Not yet implemented")
    }

    override fun getAdmins(): List<User.Administrator> {
        TODO("Not yet implemented")
    }

    override fun createUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: String) {
        TODO("Not yet implemented")
    }

    override fun getUser(id: String): User {
        TODO("Not yet implemented")
    }

    override fun updateResident(id: String, resident: User.Resident) {
        TODO("Not yet implemented")
    }

}