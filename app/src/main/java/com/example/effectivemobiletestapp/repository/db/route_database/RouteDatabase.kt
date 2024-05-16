package com.example.effectivemobiletestapp.repository.db.route_database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [RouteInfoEntity::class], version = 1, exportSchema = true)
abstract class RouteDatabase() : RoomDatabase(), RouteDatabaseContract {

    companion object {
        const val DATABASE_NAME = "route_db"
    }
}
