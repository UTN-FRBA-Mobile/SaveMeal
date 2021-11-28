package com.example.savemeal.domain

import androidx.lifecycle.ViewModel
import com.example.savemeal.ReservationDI
import com.example.savemeal.reservations.ReservationOption

class ReservationViewModel : ViewModel() {

    private val reservationRepository: ReservationRepository = ReservationDI.provideReservationRepository()

    fun getReservationDetail(reservationId: Int): ReservationOption {
        return reservationRepository.getReservationDetail(reservationId)
    }
}

