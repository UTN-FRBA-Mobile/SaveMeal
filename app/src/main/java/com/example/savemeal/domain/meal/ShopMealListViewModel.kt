package com.example.savemeal.domain.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savemeal.MealDI

class ShopMealListViewModel: ViewModel() {
    private val mealRepository: MealRepository = MealDI.provideMealRepository()

    suspend fun getMeals(): LiveData<List<MealOption>> {
        val mutableLiveData = MutableLiveData<List<MealOption>>()
        mutableLiveData.value = mealRepository.getShopMeals()
        return mutableLiveData
    }

    suspend fun deleteMeal(mealId: Int) {
        mealRepository.deleteMeal(mealId)
    }
}