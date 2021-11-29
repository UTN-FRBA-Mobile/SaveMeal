package com.example.savemeal.domain.meal

import androidx.lifecycle.ViewModel
import com.example.savemeal.MealDI
import com.example.savemeal.infrastructure.NewMeal

class ShopMealViewModel: ViewModel() {

    private val mealRepository: MealRepository = MealDI.provideMealRepository()

    suspend fun createMeal(newMeal: NewMeal) {
        mealRepository.createMeal(newMeal)
    }
}