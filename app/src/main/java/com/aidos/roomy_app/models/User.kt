package com.aidos.roomy_app.models

sealed class User(
    val id: String,
    val name: String,
    val surname: String,
    val username: String,
    val password: String
) {

    fun copy(
        id: String = this.id,
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
        id: String,
        name: String = "",
        surname: String = "",
        username: String = "",
        password: String = "",
        val dormitory: Dormitory? = null
    ) : User(id, name, surname, username, password)

    class Resident(
        id: String,
        name: String = "",
        surname: String = "",
        username: String = "",
        password: String = "",
        val room: Room? = null
    ) : User(id, name, surname, username, password) {

        fun getFullName(): String {
            return this.name + " " + this.surname
        }
    }
}
