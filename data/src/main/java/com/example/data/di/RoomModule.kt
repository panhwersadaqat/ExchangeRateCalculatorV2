package com.example.data.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
//
//    @Synchronized
//    @Provides
//    @Singleton
//    fun providesDatabase(@ApplicationContext context: Context): MyDatabase =
//        Room.databaseBuilder(
//            context,
//            MyDatabase::class.java,
//            "DB"
//        ).addMigrations(MyDatabase.migration1to2).build()
}