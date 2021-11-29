package com.example.savemeal.infrastructure

import com.example.savemeal.domain.reservation.ReservationDetail
import com.example.savemeal.domain.reservation.ReservationOption
import com.example.savemeal.domain.reservation.ReservationRepository
import com.example.savemeal.domain.reservation.ReservationService

class InMemoryReservationRepository(private val reservationService: ReservationService) :
    ReservationRepository {

    private val reservations: MutableList<ReservationDetail> = mutableListOf()

    override suspend fun getUserReservations(): List<ReservationOption> {
        reservations.clear()
        reservations.addAll(reservationService.getUserReservations())
        return reservations.toReservationOption()
    }

    override suspend fun getBusinessReservations(): List<ReservationOption> {
        reservations.clear()
        reservations.addAll(reservationService.getBusinessReservations())
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
        reservationService.createReservation(NewReservation(businessId, mealId))
    }

    override suspend fun markReservationAsDelivered(reservationId: Int) {
        reservationService.markReservationAsDelivered(reservationId)
    }

    private fun MutableList<ReservationDetail>.toReservationOption(): List<ReservationOption> {
        return this.map { ReservationOption(it.reservationId, it.comida.nombre, it.getStatus()) }
    }

    data class NewReservation(val idComercio: Int,val prodId: Int, val userId: Int = 2 )
}

