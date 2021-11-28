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
    val descripcion: String,
    val disponibles: Int,
    val expiracion: String
)
