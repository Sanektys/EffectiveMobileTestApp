package com.example.effectivemobiletestapp.repository.db

import com.example.effectivemobiletestapp.domain.DatabaseRepository
import com.example.effectivemobiletestapp.repository.db.route_database.RouteDatabaseContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DatabaseRepositoryImpl @Inject constructor(routeDbContract: RouteDatabaseContract)
    : DatabaseRepository
{
    private val routeInfoDao = routeDbContract.provideRouteInfo()


    override suspend fun retrieveLatestCountryFrom(): String = withContext(Dispatchers.IO) {
        routeInfoDao.retrieveLatestCountryFrom() ?: ""
    }

    override fun observeLatestCountryFrom(): Flow<String>
            = routeInfoDao.observeLatestCountryFrom().filterNotNull().distinctUntilChanged()

    override suspend fun changeLatestCountryFrom(country: String) = withContext(Dispatchers.IO) {
        val value = routeInfoDao.retrieveLatestCountryFrom()

        if (value == country) return@withContext

        if (value == null) {
            routeInfoDao.insertLatestCountryFrom(country)
        } else {
            routeInfoDao.updateLatestCountryFrom(country)
        }
    }
}