package com.example.savemeal.infrastructure

import com.example.savemeal.domain.meal.MealDetail
import com.example.savemeal.domain.meal.MealOption
import com.example.savemeal.domain.meal.MealRepository
import com.example.savemeal.domain.meal.MealService

class InMemoryMealRepository(private val mealService: MealService) : MealRepository {

    private var meals: MutableList<MealDetail> = mutableListOf()

    override suspend fun getAvailableMeals(): List<MealOption> {
        meals.clear()
        meals.addAll(mealService.getMeals())
        return meals.toMealOption()
    }

    override fun getMealDetail(mealId: Int): MealDetail {
        return meals.find { it.id == mealId }!!
    }

    override suspend fun getShopMeals(): List<MealOption>? {
        meals.clear()
        meals.addAll(mealService.getShopProducts())
        return meals.toMealOption()
    }

    private fun MutableList<MealDetail>.toMealOption(): List<MealOption> {
        return this.map { MealOption(it.id, it.nombre) }
    }


    override suspend fun deleteMeal(productId: Int) {
        meals.removeIf{it.id == productId }
        mealService.deleteProduct(productId)
    }

}