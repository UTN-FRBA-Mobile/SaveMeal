package com.example.savemeal.infrastructure

import com.example.savemeal.domain.MealDetail
import com.example.savemeal.domain.MealOption
import com.example.savemeal.domain.MealRepository
import com.example.savemeal.domain.MealService

class InMemoryMealRepository(private val mealService: MealService) : MealRepository {

    private val meals: MutableList<MealDetail> = mutableListOf()

    override suspend fun getAvailableMeals(): List<MealOption> {
        addMissingMeals(mealService.getMeals())
        return meals.toMealOption()
    }

    override fun getMealDetail(mealId: Int): MealDetail {
        return meals.find { it.id == mealId }!!
    }

    private fun addMissingMeals(newMeals: List<MealDetail>) {
        val missingMeals = newMeals.filterNot {
            meals.any { meal ->
                meal.id == it.id
            }
        }

        meals.addAll(missingMeals)
    }

    private fun MutableList<MealDetail>.toMealOption(): List<MealOption> {
        return this.map { MealOption(it.id, it.nombre) }
    }

}
