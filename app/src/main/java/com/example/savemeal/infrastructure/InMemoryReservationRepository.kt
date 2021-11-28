package com.example.savemeal.infrastructure

import com.example.savemeal.domain.reservation.ReservationRepository
import com.example.savemeal.domain.reservation.ReservationService
import com.example.savemeal.domain.reservation.ReservationOption

class InMemoryReservationRepository(private val reservationService: ReservationService) :
    ReservationRepository {

    private val reservations: MutableList<ReservationOption> = mutableListOf()

    private fun addMissingReservations(newReservations: List<ReservationOption>) {
        val missingMeals = newReservations.filterNot {
            reservations.any { res ->
                res.name == it.name
            }
        }

        reservations.addAll(missingMeals)
    }


    override suspend fun getReservations(): List<ReservationOption> {
        addMissingReservations(reservationService.getReservations())
        return reservations
    }

    override fun getReservationDetail(reservationId: Int): ReservationOption {
        //return reservations.find { it.id == reservationId }!!
        return reservations.first()
    }

}
