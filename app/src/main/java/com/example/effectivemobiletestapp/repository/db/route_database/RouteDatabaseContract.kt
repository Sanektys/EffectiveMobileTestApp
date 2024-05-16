package com.example.effectivemobiletestapp.repository.db.route_database


interface RouteDatabaseContract {
    fun provideRouteInfo(): RouteInfoDao
}