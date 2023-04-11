package com.aidos.roomy_app.frameworks.dagger.subcomponents

import com.aidos.roomy_app.data.*
import com.aidos.roomy_app.data.remote_data_source.DormitoryRemoteDataSource
import com.aidos.roomy_app.data.remote_data_source.PaymentsRemoteDataSource
import com.aidos.roomy_app.data.remote_data_source.RequestRemoteDataSource
import com.aidos.roomy_app.data.remote_data_source.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

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
}