package com.example.savemeal.domain.reservation

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.DELETE

interface ReservationService {

    @GET("reservation/user/2")
    suspend fun getReservations(): List<ReservationDetail>

    @DELETE("reservation/:reservationId")
    fun cancelReservation(reservationId: Int)


    companion object {
        private const val BASE_URL = "https://save-meal-api.herokuapp.com/"

        fun create(): ReservationService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ReservationService::class.java)
        }
    }
}