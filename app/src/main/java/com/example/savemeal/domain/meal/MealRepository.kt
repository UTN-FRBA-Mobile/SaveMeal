package com.example.savemeal.domain.meal

import com.example.savemeal.ShopProducts.ShopProduct

interface MealRepository {

    suspend fun getAvailableMeals(): List<MealOption>
    fun getMealDetail(mealId: Int): MealDetail
    suspend fun getShopProducts(): List<MealOption>?
    suspend fun deleteProduct(mealId: Int)
}
