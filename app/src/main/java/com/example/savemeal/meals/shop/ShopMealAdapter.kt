package com.example.savemeal.meals.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.databinding.ShopProductItemBinding
import com.example.savemeal.domain.meal.MealOption
import com.example.savemeal.meals.consumer.MealDiffCallback

class ShopMealAdapter(
    private val onDeleteItem: (Int) -> Unit,
    private val onShowItem: (Int) -> Unit
) : ListAdapter<MealOption, RecyclerView.ViewHolder>(MealDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShopMealViewHolder(
            ShopProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shopMeal = getItem(position)
        (holder as ShopMealViewHolder).bind(shopMeal, onDeleteItem, onShowItem)
    }

    class ShopMealViewHolder(
        val binding: ShopProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MealOption, onDeleteItem: (Int) -> Unit, onShowItem: (Int) -> Unit) {
            binding.productName.text = item.nombre
            binding.deleteButton.setOnClickListener {
                onDeleteItem(item.id)
            }
            binding.showButton.setOnClickListener {
                onShowItem(item.id)
            }
        }
    }
}

