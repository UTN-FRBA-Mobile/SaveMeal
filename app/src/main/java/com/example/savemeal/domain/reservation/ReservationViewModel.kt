package com.example.savemeal.domain.reservation

import androidx.lifecycle.ViewModel
import com.example.savemeal.ReservationDI

class ReservationViewModel : ViewModel() {

    private val reservationRepository: ReservationRepository = ReservationDI.provideReservationRepository()

    fun getReservationDetail(reservationId: Int): ReservationDetail {
        return reservationRepository.getReservationDetail(reservationId)
    }
}

