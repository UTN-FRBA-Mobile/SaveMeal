package com.example.savemeal.domain.reservation

import com.example.savemeal.infrastructure.InMemoryReservationRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ReservationService {

    @GET("reservation/user/2")
    suspend fun getUserReservations(): List<ReservationDetail>
    
    @GET("reservation/business/1")
    suspend fun getBusinessReservations(): List<ReservationDetail>

    @DELETE("reservation/{id}")
    suspend fun cancelReservation(
        @Path("id")
        reservationId: Int)

    @POST("reservation/token")
    suspend fun createReservation(@Body reservation: InMemoryReservationRepository.NewReservation)


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