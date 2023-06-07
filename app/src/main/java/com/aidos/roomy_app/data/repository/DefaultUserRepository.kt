package com.aidos.roomy_app.data.repository

import com.aidos.roomy_app.data.remote_data_source.UserRemoteDataSource
import com.aidos.roomy_app.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUserRepository @Inject constructor(
    private val dataSource: UserRemoteDataSource
) : UserRepository {
    private val _currentResidentFlow = MutableStateFlow(User.Resident(-1))

    override fun getCurrentUserFlow(): StateFlow<User.Resident> = _currentResidentFlow

    override suspend fun getAllUsers(): List<User> = dataSource.fetchUsers()

    override suspend fun getResidents(): List<User.Resident> = dataSource.fetchUsers().filterIsInstance(User.Resident::class.java)

    override suspend fun getAdmins(): List<User.Administrator> = dataSource.fetchUsers().filterIsInstance(User.Administrator::class.java)

    override suspend fun createUser(user: User) = dataSource.createUser(user)

    override suspend fun deleteUser(id: String) = dataSource.removeUser(id)

    override suspend fun getResidentByLoginData(login: String, password: String): User.Resident? {
        val user = dataSource.getResidentByLoginData(login, password)
        if (user != null && user is User.Resident)
            _currentResidentFlow.value = user
        return user
    }

    override suspend fun getAdminByLoginData(login: String, password: String): User.Administrator?
        = dataSource.getAdminByLoginData(login, password)


    override suspend fun updateResident(resident: User.Resident, placeId: String) {
        val properties = "{\"placeId:\"\"$placeId\"}"
        dataSource.updateResident(resident, properties)
    }

}