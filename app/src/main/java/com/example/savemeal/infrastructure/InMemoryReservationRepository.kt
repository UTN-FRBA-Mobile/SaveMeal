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


    override suspend fun getUserReservations(): List<ReservationOption> {
        addMissingReservations(reservationService.getUserReservations())
        return reservations.toReservationOption()
    }

    override suspend fun getBusinessReservations(): List<ReservationOption> {
        addMissingReservations(reservationService.getBusinessReservations())
        return reservations.toReservationOption()
    }

    override fun getReservationDetail(reservationId: Int): ReservationDetail {
        return reservations.find { it.reservationId == reservationId }!!
    }

    override suspend fun cancelReservation(reservationId: Int) {
       reservations.removeIf{it.reservationId == reservationId }
        reservationService.cancelReservation(reservationId)
    }

    override suspend fun makeReservation(mealId: Int, businessId: Int) {
        reservationService.createReservation(NewReservation(mealId, businessId))
    }

    override suspend fun markReservationAsDelivered(reservationId: Int) {
        reservationService.markReservationAsDelivered(reservationId)
    }

    private fun MutableList<ReservationDetail>.toReservationOption(): List<ReservationOption> {
        return this.map { ReservationOption(it.reservationId, it.comida.nombre, it.getStatus()) }
    }

    data class NewReservation(val idComercio: Int,val prodId: Int, val userId: Int = 2 )
}

