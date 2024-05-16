package com.example.effectivemobiletestapp.di.modules

import com.example.effectivemobiletestapp.di.ApplicationScope
import com.example.effectivemobiletestapp.domain.MockyApiRepository
import com.example.effectivemobiletestapp.repository.api.MockyApiRepositoryImpl
import com.example.effectivemobiletestapp.repository.api.mocky_api.MockyApi
import com.example.effectivemobiletestapp.repository.api.utils.OkHttpBuilder
import com.example.effectivemobiletestapp.repository.api.utils.RetrofitBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit


@Module(includes = [MockyApiCastModule::class])
class MockyApiModule {

    @[Provides ApplicationScope]
    fun provideOkHttpClient(): OkHttpClient = OkHttpBuilder.build()

    @[Provides ApplicationScope]
    fun provideRetrofitForMockyApi(client: OkHttpClient): Retrofit
            = RetrofitBuilder.buildForMockyApi(client)

    @Provides
    fun provideMockyApi(retrofit: Retrofit) = retrofit.create(MockyApi::class.java)
}

@Module
interface MockyApiCastModule {

    @Binds
    fun bindMockyApiRepository(mockyApiRepositoryImpl: MockyApiRepositoryImpl): MockyApiRepository
}