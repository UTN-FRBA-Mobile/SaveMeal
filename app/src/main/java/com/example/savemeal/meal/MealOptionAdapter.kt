package com.example.savemeal.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.R
import com.example.savemeal.databinding.MealOptionItemBinding

class MealOptionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val options = listOf(
        MealOption("Opcion 1"),
        MealOption("Opcion 2"),
        MealOption("Opcion 3"),
        MealOption("Opcion 4"),
        MealOption("Opcion 5"),
        MealOption("Opcion 6"),
        MealOption("Opcion 7"),
        MealOption("Opcion 8"),
        MealOption("Opcion 9"),
        MealOption("Opcion 10")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MealOptionViewHolder(
            MealOptionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mealOption = options[position]
        (holder as MealOptionViewHolder).bind(mealOption)

        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_availableMealsFragment_to_productViewFragment)
        )
    }

    override fun getItemCount(): Int {
        return options.count()
    }

    class MealOptionViewHolder(
        val binding: MealOptionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MealOption) {
            binding.mealType.text = item.type
        }
    }

}

data class MealOption(val type: String)
