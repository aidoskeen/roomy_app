package com.aidos.roomy_app.frameworks.dagger.subcomponents

import com.aidos.roomy_app.data.*
import com.aidos.roomy_app.data.remote_data_source.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindDormitoryRepository(repository: DefaultDormitoryRepository): DormitoryRepository

    @Singleton
    @Binds
    abstract fun bindDormitoryDataSource(dataSource: DormitoryRemoteData): DormitoryRemoteDataSource
}
    @Module
    class DataSourceModule {
    @Provides
    @Singleton
    fun provideRoomRemoteDataSource(
        hostConnection: HostConnection
    ): RoomsRemoteDataSource = RoomsRemoteData(hostConnection)

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        hostConnection: HostConnection
    ) : UserRemoteDataSource = UserRemoteData(hostConnection)

    @Provides
    @Singleton
    fun provideRequestRemoteDataSource(
        hostConnection: HostConnection
    ) : RequestRemoteDataSource = RequestRemoteData(hostConnection)

    @Provides
    @Singleton
    fun providePaymentRemoteDataSource(
        hostConnection: HostConnection
    ) : PaymentsRemoteDataSource = PaymentRemoteData(hostConnection)


    @Provides
    @Singleton
    fun provideRoomRepository(
        roomsRemoteDataSource: RoomsRemoteDataSource
    ) : RoomRepository = DefaultRoomRepository(
        roomsRemoteDataSource
    )

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource
    ) : UserRepository = DefaultUserRepository(
        userRemoteDataSource
    )

    @Provides
    @Singleton
    fun provideRequestRepository(
        requestRemoteDataSource: RequestRemoteDataSource
    ) : RequestRepository = DefaultRequestRepository(
        requestRemoteDataSource
    )

    @Provides
    @Singleton
    fun providePaymentRepository(
        paymentsRemoteDataSource: PaymentsRemoteDataSource
    ) : PaymentRepository = DefaultPaymentRepository(
        paymentsRemoteDataSource
    )

    @Provides
    @Singleton
    fun provideHostConnection() : HostConnection = HostConnection()
}