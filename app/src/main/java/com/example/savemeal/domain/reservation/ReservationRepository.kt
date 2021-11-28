package com.example.savemeal.domain.reservation

interface ReservationRepository {

    suspend fun getReservations(): List<ReservationOption>
    fun getReservationDetail(reservationId: Int): ReservationDetail
    suspend fun cancelReservation(reservationId: Int)
}
