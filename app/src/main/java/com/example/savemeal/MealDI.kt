package com.example.savemeal

import com.example.savemeal.domain.meal.MealRepository
import com.example.savemeal.domain.meal.MealService
import com.example.savemeal.infrastructure.InMemoryMealRepository

object MealDI {

    private val mealService = MealService.create()
    private val mealRepository = InMemoryMealRepository(mealService)

    fun provideMealRepository(): MealRepository = mealRepository
}