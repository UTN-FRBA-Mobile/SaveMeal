package com.example.savemeal

import com.example.savemeal.domain.MealRepository
import com.example.savemeal.domain.MealService
import com.example.savemeal.infrastructure.InMemoryMealRepository

object MealDI {

    private val mealService = MealService.create()
    private val mealRepository = InMemoryMealRepository(mealService)

    fun provideMealRepository(): MealRepository = mealRepository
}