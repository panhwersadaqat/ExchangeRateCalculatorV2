package com.example.data.di


import com.example.data.repository.CurrencyRemoteSourceImp
import com.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCurrencyRepository(currencyRemoteSourceImp: CurrencyRemoteSourceImp): CurrencyRepository

}
