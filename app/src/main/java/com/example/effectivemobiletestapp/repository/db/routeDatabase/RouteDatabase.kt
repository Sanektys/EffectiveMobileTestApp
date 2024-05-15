package com.example.effectivemobiletestapp.repository.db.routeDatabase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [RouteInfoEntity::class], version = 1, exportSchema = true)
abstract class RouteDatabase() : RoomDatabase(), RouteDatabaseContract {

    companion object {
        const val DATABASE_NAME = "route_db"
    }
}
