package com.example.savemeal.domain.meal

import com.example.savemeal.infrastructure.NewMeal
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MealService {

    @GET("products/business/1")
    suspend fun getShopProducts(): List<MealDetail>

    @GET("products/available")
    suspend fun getMeals(): List<MealDetail>

    @DELETE("product/{id}")
    suspend fun deleteProduct(
        @Path("id")
        productId: Int)

    @POST("product")
    suspend fun createMeal(@Body meal: NewMeal)

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