package com.aidos.roomy_app.data

import com.aidos.roomy_app.models.Dormitory

interface DormitoryRepository {

    fun getDormitories(): List<Dormitory>

    fun getDormitory(id: Int): Dormitory

}