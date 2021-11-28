package com.example.savemeal.domain.meal

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MealService {

    @GET("products/available")
    suspend fun getMeals(): List<MealDetail>

    companion object {
        private const val BASE_URL = "https://save-meal-api.herokuapp.com/"

        fun create(): MealService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MealService::class.java)
        }
    }
}