package com.example.savemeal.domain.meal

import androidx.lifecycle.ViewModel
import com.example.savemeal.MealDI

class MealViewModel : ViewModel() {

    private val mealRepository: MealRepository = MealDI.provideMealRepository()

    fun getMealDetail(mealId: Int): MealDetail {
        return mealRepository.getMealDetail(mealId)
    }
}

data class MealDetail(
    val id: Int,
    val nombre: String,
    val detalle: String,
    val disponibles: Int,
    val expiracion: String,
    val porciones: String,
    val image: String,
    val business: Business
)

data class Business(
    val address: String,
    val businessHours: String,
    val businessName: String
)
