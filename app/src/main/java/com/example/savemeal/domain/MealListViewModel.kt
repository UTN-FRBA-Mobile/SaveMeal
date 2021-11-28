package com.example.savemeal.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savemeal.MealDI

class MealListViewModel : ViewModel() {

    private val mealRepository: MealRepository = MealDI.provideMealRepository()

    suspend fun getAvailableMeals(): LiveData<List<MealOption>> {
        val mutableLiveData = MutableLiveData<List<MealOption>>()
        mutableLiveData.value = mealRepository.getAvailableMeals()
        return mutableLiveData
    }

}

