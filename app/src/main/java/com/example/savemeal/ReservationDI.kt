package com.example.savemeal

import com.example.savemeal.domain.reservation.ReservationRepository
import com.example.savemeal.domain.reservation.ReservationService
import com.example.savemeal.infrastructure.InMemoryReservationRepository

object ReservationDI {

    private val reservationService = ReservationService.create()
    private val reservationRepository = InMemoryReservationRepository(reservationService)

    fun provideReservationRepository(): ReservationRepository = reservationRepository
}