package com.example.effectivemobiletestapp.domain.interactors

import com.example.effectivemobiletestapp.domain.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DbInteractor @Inject constructor(private val databaseRepository: DatabaseRepository) {

    suspend fun retrieveLatestCountryFrom(): String = databaseRepository.retrieveLatestCountryFrom()

    fun observeLatestCountryFrom(): Flow<String> = databaseRepository.observeLatestCountryFrom()

    suspend fun changeLatestCountryFrom(country: String)
            = databaseRepository.changeLatestCountryFrom(country)
}