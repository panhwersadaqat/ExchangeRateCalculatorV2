package com.example.data.di


import com.example.data.network.ApiInterface
import com.example.data.network.RequestInterceptor
import com.example.data.network.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(
        tokenAuthenticator: TokenAuthenticator,
        requestInterceptor: RequestInterceptor
    ) = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(requestInterceptor)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideApiInterface(@WifiNetworkServicesQualifier retrofit: Retrofit) =
        retrofit.create<ApiInterface>(ApiInterface::class.java)

}