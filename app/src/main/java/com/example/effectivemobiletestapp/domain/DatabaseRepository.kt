package com.example.effectivemobiletestapp.domain

import kotlinx.coroutines.flow.Flow


interface DatabaseRepository {

    suspend fun retrieveLatestCountryFrom(): String

    fun observeLatestCountryFrom(): Flow<String>

    suspend fun changeLatestCountryFrom(country: String)
}