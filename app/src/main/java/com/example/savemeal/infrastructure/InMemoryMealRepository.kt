package com.example.savemeal.infrastructure

import com.example.savemeal.ShopProducts.ShopProduct
import com.example.savemeal.domain.meal.MealDetail
import com.example.savemeal.domain.meal.MealOption
import com.example.savemeal.domain.meal.MealRepository
import com.example.savemeal.domain.meal.MealService

class InMemoryMealRepository(private val mealService: MealService) : MealRepository {

    private val meals: MutableList<MealDetail> = mutableListOf()

    override suspend fun getAvailableMeals(): List<MealOption> {
        addMissingMeals(mealService.getMeals())
        return meals.toMealOption()
    }

    override fun getMealDetail(mealId: Int): MealDetail {
        return meals.find { it.id == mealId }!!
    }

    override suspend fun getShopProducts(): List<MealOption>? {
        addMissingMeals(mealService.getShopProducts())
        return meals.toMealOption()
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

    override suspend fun deleteProduct(productId: Int) {
        meals.removeIf{it.id == productId }
        mealService.deleteProduct(productId)
    }

}
