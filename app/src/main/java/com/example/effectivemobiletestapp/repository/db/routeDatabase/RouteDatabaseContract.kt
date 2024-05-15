package com.example.effectivemobiletestapp.repository.db.routeDatabase


interface RouteDatabaseContract {
    fun provideRouteInfo(): RouteInfoDao
}