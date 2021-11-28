package com.example.savemeal.domain.reservation

data class ReservationOption(
    val reservationId: Int,
    val name: String = "Falta mapear",
    val status: String = "Falta mapear"
)
