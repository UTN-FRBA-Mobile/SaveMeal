package com.example.savemeal.domain.reservation

interface ReservationRepository {

    suspend fun getReservations(): List<ReservationOption>
    fun getReservationDetail(reservationId: Int): ReservationDetail
    fun cancelReservation(reservationId: Int)
}
