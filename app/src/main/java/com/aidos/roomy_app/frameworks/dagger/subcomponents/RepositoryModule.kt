package com.aidos.roomy_app.frameworks.dagger.subcomponents

import com.aidos.roomy_app.data.*
import com.aidos.roomy_app.data.remote_data_source.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideDormitoryRemoteDataSource(
        hostConnection: HostConnection
    ): DormitoryRemoteDataSource = DormitoryRemoteData(hostConnection)

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
    fun provideDormitoryRepository(
        dormitoryRemoteDataSource: DormitoryRemoteDataSource,
    ) : DormitoryRepository = DefaultDormitoryRepository(
        dormitoryRemoteDataSource
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