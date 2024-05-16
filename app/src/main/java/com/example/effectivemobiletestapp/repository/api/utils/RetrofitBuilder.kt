package com.example.effectivemobiletestapp.repository.api.utils

import com.example.effectivemobiletestapp.repository.api.mocky_api.MockyApiConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    fun build(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun buildForMockyApi(client: OkHttpClient) = build(
        baseUrl = MockyApiConstants.BASE_URL,
        client = client
    )
}