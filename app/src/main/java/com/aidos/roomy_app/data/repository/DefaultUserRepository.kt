package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.data.remote_data_source.UserRemoteDataSource
import com.aidos.roomy_app.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUserRepository @Inject constructor(
    private val dataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun getAllUsers(): List<User> = dataSource.fetchUsers()

    override suspend fun getResidents(): List<User.Resident> = dataSource.fetchUsers().filterIsInstance(User.Resident::class.java)

    override suspend fun getAdmins(): List<User.Administrator> = dataSource.fetchUsers().filterIsInstance(User.Administrator::class.java)

    override suspend fun createUser(user: User) { dataSource.createUser(user) }

    override suspend fun deleteUser(id: String) = dataSource.removeUser(id)


    override suspend fun getUserByLoginData(login: String, password: String): User = dataSource.getUserByLoginData(login, password)

    override suspend fun updateResident(id: Int, resident: User.Resident) = dataSource.updateResident(id, resident)

}