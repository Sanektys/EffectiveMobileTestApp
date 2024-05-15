package com.example.effectivemobiletestapp.di

import android.content.Context
import androidx.room.Room
import com.example.effectivemobiletestapp.domain.DatabaseRepository
import com.example.effectivemobiletestapp.repository.db.DatabaseRepositoryImpl
import com.example.effectivemobiletestapp.repository.db.routeDatabase.RouteDatabase
import com.example.effectivemobiletestapp.repository.db.routeDatabase.RouteDatabaseContract
import com.example.effectivemobiletestapp.repository.db.routeDatabase.RouteInfoDao
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [DatabaseRepositoryModule::class])
class DatabaseModule {

    @[Provides ApplicationScope]
    fun provideRouteDatabase(context: Context): RouteDatabase = Room
        .databaseBuilder(context, RouteDatabase::class.java, RouteDatabase.DATABASE_NAME)
        .build()

    @Provides
    fun provideRouteInfoDao(routeDatabase: RouteDatabase): RouteInfoDao
            = routeDatabase.provideRouteInfo()
}

@Module
interface DatabaseRepositoryModule {

    @Binds
    fun provideRouteDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository

    @Binds
    fun provideRouteDatabaseContract(routeDatabase: RouteDatabase): RouteDatabaseContract
}