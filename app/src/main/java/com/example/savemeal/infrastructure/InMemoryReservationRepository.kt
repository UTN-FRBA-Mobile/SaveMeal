package com.example.savemeal.infrastructure

import com.example.savemeal.domain.reservation.ReservationDetail
import com.example.savemeal.domain.reservation.ReservationOption
import com.example.savemeal.domain.reservation.ReservationRepository
import com.example.savemeal.domain.reservation.ReservationService

class InMemoryReservationRepository(private val reservationService: ReservationService) :
    ReservationRepository {

    private val reservations: MutableList<ReservationDetail> = mutableListOf()

    private fun addMissingReservations(newReservations: List<ReservationDetail>) {
        val missingReservations = newReservations.filterNot {
            reservations.any { res ->
                res.reservationId == it.reservationId
            }
        }

        reservations.addAll(missingReservations)
    }


    override suspend fun getReservations(): List<ReservationOption> {
        addMissingReservations(reservationService.getReservations())
        return reservations.toReservationOption()
    }

    override fun getReservationDetail(reservationId: Int): ReservationDetail {
        return reservations.find { it.reservationId == reservationId }!!
    }

    private fun MutableList<ReservationDetail>.toReservationOption(): List<ReservationOption> {
        return this.map { ReservationOption(it.reservationId, it.comida.nombre, it.getStatus()) }
    }
}

