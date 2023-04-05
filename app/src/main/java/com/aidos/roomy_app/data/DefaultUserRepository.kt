package com.aidos.roomy_app.data

import com.aidos.roomy_app.data.remote_data_source.UserRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUserRepository @Inject constructor(
    private val dataSource: UserRemoteDataSource
){

}