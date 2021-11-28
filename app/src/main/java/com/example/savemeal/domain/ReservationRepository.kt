package com.example.savemeal.domain

import com.example.savemeal.reservations.ReservationOption

interface ReservationRepository {

    suspend fun getReservations(): List<ReservationOption>
    fun getReservationDetail(reservationId: Int): ReservationOption
}
