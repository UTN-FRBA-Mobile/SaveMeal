package com.example.savemeal.domain.meal

import androidx.lifecycle.ViewModel
import com.example.savemeal.MealDI

class MealViewModel : ViewModel() {

    private val mealRepository: MealRepository = MealDI.provideMealRepository()

    fun getMealDetail(mealId: Int): MealDetail {
        return mealRepository.getMealDetail(mealId)
    }
}

