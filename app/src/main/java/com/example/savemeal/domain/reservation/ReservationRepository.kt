package com.example.savemeal.domain.reservation

interface ReservationRepository {

    suspend fun getUserReservations(): List<ReservationOption>
    suspend fun getBusinessReservations(): List<ReservationOption>
    fun getReservationDetail(reservationId: Int): ReservationDetail
    suspend fun cancelReservation(reservationId: Int)
    suspend fun makeReservation(mealId: Int, businessId: Int)
    suspend fun markReservationAsDelivered(reservationId: Int)
}
