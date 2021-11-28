package com.example.savemeal.domain.product

import com.example.savemeal.infrastructure.InMemoryReservationRepository
import com.example.savemeal.infrastructure.InMemoryShopProductRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ShopProductService {

    @GET("products/business/1")
    suspend fun getShopProducts(): List<ShopProductDetail>

    @DELETE("product/{id}")
    suspend fun deleteProduct(
        @Path("id")
        productId: Int)

    @POST("product")
    suspend fun createProduct(@Body product: InMemoryShopProductRepository.NewProduct)

    companion object {
        private const val BASE_URL = "https://save-meal-api.herokuapp.com/"

        fun create(): ShopProductService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopProductService::class.java)
        }
    }
}