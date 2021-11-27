package com.example.savemeal.domain

interface MealRepository {

    suspend fun getAvailableMeals(): List<MealOption>

}
