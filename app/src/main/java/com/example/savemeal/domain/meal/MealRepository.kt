package com.example.savemeal.domain.meal

import com.example.savemeal.infrastructure.NewMeal

interface MealRepository {

    suspend fun getAvailableMeals(): List<MealOption>
    fun getMealDetail(mealId: Int): MealDetail
    suspend fun getShopMeals(): List<MealOption>?
    suspend fun deleteMeal(mealId: Int)
    suspend fun createMeal(meal: NewMeal)
}
