package com.example.effectivemobiletestapp.repository.db.route_database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RouteInfoDao {
    @Query("SELECT ${RouteInfoEntity.COLUMN_LAST_COUNTRY_FROM} " +
            "FROM ${RouteInfoEntity.TABLE_NAME} " +
            "WHERE ${RouteInfoEntity.COLUMN_ID} = 0")
    suspend fun retrieveLatestCountryFrom(): String?

    @Query("SELECT ${RouteInfoEntity.COLUMN_LAST_COUNTRY_FROM} " +
            "FROM ${RouteInfoEntity.TABLE_NAME} " +
            "WHERE ${RouteInfoEntity.COLUMN_ID} = 0")
    fun observeLatestCountryFrom(): Flow<String?>

    @Query("INSERT INTO ${RouteInfoEntity.TABLE_NAME}(" +
            "${RouteInfoEntity.COLUMN_ID}, ${RouteInfoEntity.COLUMN_LAST_COUNTRY_FROM}" +
            ") " +
            "VALUES(0, :country)")
    suspend fun insertLatestCountryFrom(country: String)

    @Query("UPDATE ${RouteInfoEntity.TABLE_NAME} " +
            "SET ${RouteInfoEntity.COLUMN_LAST_COUNTRY_FROM} = :country " +
            "WHERE ${RouteInfoEntity.COLUMN_ID} = 0")
    suspend fun updateLatestCountryFrom(country: String)
}