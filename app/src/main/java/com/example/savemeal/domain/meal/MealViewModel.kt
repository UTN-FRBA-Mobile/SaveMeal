package com.example.savemeal.domain.meal

import androidx.lifecycle.ViewModel
import com.example.savemeal.MealDI
import com.example.savemeal.ReservationDI
import com.example.savemeal.domain.reservation.ReservationRepository

class MealViewModel : ViewModel() {

    private val mealRepository: MealRepository = MealDI.provideMealRepository()
    private val reservationRepository: ReservationRepository =
        ReservationDI.provideReservationRepository()

    fun getMealDetail(mealId: Int): MealDetail {
        return mealRepository.getMealDetail(mealId)
    }

    suspend fun makeReservation(meal: MealDetail) {
        reservationRepository.makeReservation(meal.id, meal.business.id)
    }
}

