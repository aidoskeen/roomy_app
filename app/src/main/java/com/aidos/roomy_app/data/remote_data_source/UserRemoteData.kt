package com.aidos.roomy_app.data.remote_data_source

import android.util.Log
import com.aidos.roomy_app.data.Constants.HOST_ADDRESS
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
    private val residentDeserializer = UserDeserializer().getResidentDeserializer()
    private val adminDeserializer = UserDeserializer().getAdminDeserializer()
    private


    companion object {
        private const val URL_LOGIN = "${HOST_ADDRESS}/user/authentication"
        private const val URL_REGISTRATION = "${HOST_ADDRESS}/user/registration"
        private const val URL_UPDATE = "${HOST_ADDRESS}/user/update"
    }

    override suspend fun fetchUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUsers(users: List<User>) {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(user: User): HostActionStatus {
        val gson = GsonBuilder().create()
        val userJson = gson.toJson(user)
        val response = withContext(dispatcher) {
            hostConnection.sendPost(URL_REGISTRATION, userJson.toString())
        }

        return if (response == "Error") {
            Log.e("UserRemoteData", "Connection error")
            HostActionStatus.ERROR
        } else HostActionStatus.SUCCESS
    }

    override suspend fun removeUser(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getResidentByLoginData(login: String, password: String): User.Resident? {
        val gson = GsonBuilder()
            .registerTypeAdapter(User.Resident::class.java, residentDeserializer)
            .create()
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

    override suspend fun getAdminByLoginData(login: String, password: String): User.Administrator? {
        val gson = GsonBuilder()
            .registerTypeAdapter(User.Administrator::class.java, adminDeserializer)
            .create()
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
        else gson.fromJson(response, User.Administrator::class.java)
    }

    override suspend fun updateResident(resident: User.Resident, properties: String) {
        val response = withContext(dispatcher) {
            hostConnection.sendPut(URL_UPDATE + "/${resident.id}", properties)
        }
    }

}