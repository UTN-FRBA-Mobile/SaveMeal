package com.example.savemeal.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savemeal.infrastructure.InMemoryMealRepository

class MealListViewModel : ViewModel() {

    private val mealRepository: MealRepository = InMemoryMealRepository(MealService.create())

    suspend fun getAvailableMeals(): LiveData<List<MealOption>> {
        val mutableLiveData = MutableLiveData<List<MealOption>>()
        mutableLiveData.value = mealRepository.getAvailableMeals()
        return mutableLiveData
    }

}

