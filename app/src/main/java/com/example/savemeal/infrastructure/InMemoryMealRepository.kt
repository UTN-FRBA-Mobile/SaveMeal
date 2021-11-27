package com.example.savemeal.infrastructure

import com.example.savemeal.domain.MealOption
import com.example.savemeal.domain.MealRepository
import com.example.savemeal.domain.MealService

class InMemoryMealRepository(private val mealService: MealService) : MealRepository {

    private val meals: MutableList<MealOption> = mutableListOf()

    override suspend fun getAvailableMeals(): List<MealOption> {
        addMissingMeals(mealService.getMeals())
        return meals
    }

    private fun addMissingMeals(newMeals: List<MealOption>) {
        val missingMeals = newMeals.filterNot {
            meals.any { meal ->
                meal.id == it.id
            }
        }

        meals.addAll(missingMeals)
    }

}
