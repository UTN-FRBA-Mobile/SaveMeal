package com.example.savemeal.meals.consumer

import androidx.recyclerview.widget.DiffUtil
import com.example.savemeal.domain.meal.MealOption

class MealDiffCallback : DiffUtil.ItemCallback<MealOption>() {

    override fun areItemsTheSame(oldItem: MealOption, newItem: MealOption): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MealOption, newItem: MealOption): Boolean {
        return oldItem == newItem
    }
}