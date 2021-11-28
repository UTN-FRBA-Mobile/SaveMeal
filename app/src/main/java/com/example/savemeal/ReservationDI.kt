package com.example.savemeal

import com.example.savemeal.domain.ReservationRepository
import com.example.savemeal.domain.ReservationService
import com.example.savemeal.infrastructure.InMemoryReservationRepository

object ReservationDI {

    private val reservationService = ReservationService.create()
    private val reservationRepository = InMemoryReservationRepository(reservationService)

    fun provideReservationRepository(): ReservationRepository = reservationRepository
}