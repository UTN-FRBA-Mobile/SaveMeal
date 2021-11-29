package com.example.savemeal.domain.meal

interface MealRepository {

    suspend fun getAvailableMeals(): List<MealOption>
    fun getMealDetail(mealId: Int): MealDetail
    suspend fun getShopMeals(): List<MealOption>?
    suspend fun deleteMeal(mealId: Int)
}
