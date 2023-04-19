package com.aidos.roomy_app.frameworks.dagger.subcomponents

import com.aidos.roomy_app.data.remote_data_source.*
import com.aidos.roomy_app.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindDormitoryRepository(repository: DefaultDormitoryRepository): DormitoryRepository

    @Singleton
    @Binds
    abstract fun bindRoomRepository(repository: DefaultRoomRepository): RoomRepository

    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: DefaultUserRepository): UserRepository

    @Singleton
    @Binds
    abstract fun bindRequestRepository(repository: DefaultRequestRepository): RequestRepository

    @Singleton
    @Binds
    abstract fun bindPaymentRepository(repository: DefaultPaymentRepository): PaymentRepository

    @Singleton
    @Binds
    abstract fun bindPlaceRepository(repository: DefaultPlaceRepository): PlaceRepository
}

@Module
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindDormitoryDataSource(dataSource: DormitoryRemoteData): DormitoryRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindUserDataSource(dataSource: UserRemoteData): UserRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindRoomDataSource(dataSource: RoomsRemoteData): RoomsRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindRequestDataSource(dataSource: RequestRemoteData): RequestRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindPaymentDataSource(dataSource: PaymentRemoteData): PaymentsRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindPlaceDataSource(dataSource: PlaceRemoteData): PlaceRemoteDataSource

}

@Module
class ConnectionModule {
    @Provides
    @Singleton
    fun provideHostConnection() : HostConnection = HostConnection()
}