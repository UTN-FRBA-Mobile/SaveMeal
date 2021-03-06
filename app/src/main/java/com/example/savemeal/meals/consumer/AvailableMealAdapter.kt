package com.example.savemeal.meals.consumer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.R
import com.example.savemeal.databinding.MealOptionItemBinding
import com.example.savemeal.domain.meal.MealOption

class AvailableMealAdapter : ListAdapter<MealOption, RecyclerView.ViewHolder>(MealDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MealOptionViewHolder(
            MealOptionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mealOption = getItem(position)
        (holder as MealOptionViewHolder).bind(mealOption)

        val bundle = bundleOf("id" to mealOption.id, "show_buttons" to true)
        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_availableMealsFragment_to_productViewFragment,
                bundle
            )
        )
    }

    class MealOptionViewHolder(
        val binding: MealOptionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MealOption) {
            binding.mealType.text = item.nombre
        }
    }

}

