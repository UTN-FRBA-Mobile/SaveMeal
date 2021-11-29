package com.example.savemeal.ShopProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.databinding.ShopProductItemBinding
import com.example.savemeal.domain.meal.MealOption

class ShopProductsAdapter(
    private val onDeleteItem: (Int) -> Unit,
    private val onShowItem: (Int) -> Unit
) : ListAdapter<MealOption, RecyclerView.ViewHolder>(ProductsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShopProductViewHolder(
            ShopProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shopProduct = getItem(position)
        (holder as ShopProductViewHolder).bind(shopProduct, onDeleteItem, onShowItem)
    }

    class ShopProductViewHolder(
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

private class ProductsDiffCallback : DiffUtil.ItemCallback<MealOption>() {

    override fun areItemsTheSame(oldItem: MealOption, newItem: MealOption): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MealOption,
        newItem: MealOption
    ): Boolean {
        return oldItem == newItem
    }
}
