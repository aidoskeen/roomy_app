package com.aidos.roomy_app.data.remote_data_source

import android.util.Log
import com.aidos.roomy_app.data.DeserializationTools.UserDeserializer
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.frameworks.dagger.subcomponents.DefaultDispatcher
import com.aidos.roomy_app.models.User
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    val hostConnection: HostConnection,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : UserRemoteDataSource {
    private val deserializer = UserDeserializer().getResidentDeserializer()
    private val gson = GsonBuilder()
        .registerTypeAdapter(User.Resident::class.java, deserializer)
        .create()


    companion object {
        private const val HOST_ADDRESS = "http://192.168.0.215:8080/RoomyAppServer/"
        private const val URL_LOGIN = "${HOST_ADDRESS}user/login"
        private const val URL_REGISTRATION = "${HOST_ADDRESS}user/registration"
    }

    override suspend fun fetchUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUsers(users: List<User>) {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(user: User): HostActionStatus {
        val userJson = gson.toJson(user)
        val response = withContext(dispatcher) {
            hostConnection.sendPost(URL_REGISTRATION, userJson.toString())
        }

        return if (response == "Error") {
            Log.e("UserRemoteData", "Connection error")
            HostActionStatus.ERROR
        } else if (response.isEmpty()) {
            Log.e("UserRemoteData", "JSON is empty")
            HostActionStatus.ERROR
        } else HostActionStatus.SUCCESS
    }

    override suspend fun removeUser(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByLoginData(login: String, password: String): User? {
        val json = JsonObject()
        json.addProperty("login", login)
        json.addProperty("password", password)
        val response = withContext(dispatcher){
            hostConnection.sendPost(URL_LOGIN, json.toString())
        }

        return if (response == "Error") {
            Log.e("UserRemoteData", "Connection error")
            null
        } else if (response.isEmpty()) {
            Log.e("UserRemoteData", "JSON is empty")
            null
        }
        else gson.fromJson(response, User.Resident::class.java)
    }

    override suspend fun updateResident(resident: User.Resident) {
        TODO("Not yet implemented")
    }

}