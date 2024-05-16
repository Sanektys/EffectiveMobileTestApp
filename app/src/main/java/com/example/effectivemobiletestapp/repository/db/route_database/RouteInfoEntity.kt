package com.example.effectivemobiletestapp.repository.db.route_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = RouteInfoEntity.TABLE_NAME)
data class RouteInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int = 0,
    @ColumnInfo(name = COLUMN_LAST_COUNTRY_FROM, defaultValue = "")
    val lastCountryFrom: String
) {
    companion object {
        const val TABLE_NAME = "route_info"
        const val COLUMN_ID = "id"
        const val COLUMN_LAST_COUNTRY_FROM = "last_country_from"
    }
}
