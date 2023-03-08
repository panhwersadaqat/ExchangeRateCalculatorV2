package com.example.data.di

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Provides
    @Singleton
    @WifiNetworkServicesQualifier
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) = Retrofit.Builder()
        //.baseUrl(BuildConfig.BASE_URL)
            //TODO change base URL
        .baseUrl("https://api.getgeoapi.com/api/v2/currency/")
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    @OtherNetworkServicesQualifier
    fun providesRetrofitOther(
        gsonConverterFactory: GsonConverterFactory
    ) = Retrofit.Builder()
        //TODO change base URL
        //.baseUrl(BuildConfig.BASE_URL)
        .baseUrl("https://api.getgeoapi.com/api/v2/currency/")
        .addConverterFactory(gsonConverterFactory)
        .build()

    @JvmStatic
    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson) = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()


}