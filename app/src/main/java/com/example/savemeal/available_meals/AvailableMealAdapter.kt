package com.example.savemeal.available_meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.R
import com.example.savemeal.databinding.MealOptionItemBinding
import com.example.savemeal.domain.MealOption

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

        val bundle = bundleOf("id" to mealOption.id)
        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_availableMealsFragment_to_productViewFragment, bundle)
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

private class MealDiffCallback : DiffUtil.ItemCallback<MealOption>() {

    override fun areItemsTheSame(oldItem: MealOption, newItem: MealOption): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MealOption, newItem: MealOption): Boolean {
        return oldItem == newItem
    }
}

