package com.example.effectivemobiletestapp.di

import android.content.Context
import com.example.effectivemobiletestapp.di.modules.DatabaseModule
import com.example.effectivemobiletestapp.di.modules.MockyApiModule
import com.example.effectivemobiletestapp.ui.screens.all_tickets_list.AllTicketsListViewModel
import com.example.effectivemobiletestapp.ui.screens.home.HomeViewModel
import com.example.effectivemobiletestapp.ui.screens.search.SearchViewModel
import com.example.effectivemobiletestapp.ui.screens.selected_country.SelectedCountryViewModel
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [DatabaseModule::class, MockyApiModule::class])
interface ApplicationComponent {

    fun inject(allTicketsListViewModel: AllTicketsListViewModel)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(searchViewModel: SearchViewModel)
    fun inject(selectedCountryViewModel: SelectedCountryViewModel)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}