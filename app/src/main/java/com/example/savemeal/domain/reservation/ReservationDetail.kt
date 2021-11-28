package com.example.savemeal.domain.reservation

import com.example.savemeal.domain.meal.Business
import com.example.savemeal.domain.meal.MealDetail

data class ReservationDetail(
    val reservationId: Int,
    val delivered: Boolean,
    val token: String,
    val comida: MealDetail,
    val business: Business
) {
    fun getStatus(): String {
        return if (delivered) {
            "Entregado"
        } else {
            "Pendiente"
        }
    }
}
