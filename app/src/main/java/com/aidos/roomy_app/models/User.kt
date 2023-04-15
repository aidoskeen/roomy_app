package com.aidos.roomy_app.models

import java.io.Serializable

sealed class User(
    val id: Int,
    val name: String,
    val surname: String,
    val username: String,
    val password: String
) : Serializable {

    fun copy(
        id: Int = this.id,
        name: String = this.name,
        surname: String = this.surname,
        username: String = this.username,
        password: String = this.password
    ) : User {
        return when (this) {
            is Administrator -> Administrator(id, name, surname, username, password, dormitory)
            is Resident -> Resident(id, name, surname, username, password, room)
        }
    }

    class Administrator(
        id: Int,
        name: String = "",
        surname: String = "",
        username: String = "",
        password: String = "",
        val dormitory: Dormitory? = null
    ) : User(id, name, surname, username, password)

    class Resident(
        id: Int,
        name: String = "",
        surname: String = "",
        username: String = "",
        password: String = "",
        val room: Room? = null
    ) : User(id, name, surname, username, password), Serializable {

        fun getFullName(): String {
            return this.name + " " + this.surname
        }
    }
}
