package com.example.savemeal.ShopProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.databinding.ShopProductItemBinding

class ShopProductsAdapter(
    private val onDeleteItem: (Int) -> Unit,
    private val onShowItem: (Int) -> Unit
) : ListAdapter<ShopProduct, RecyclerView.ViewHolder>(ProductsDiffCallback()) {


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

        fun bind(item: ShopProduct, onDeleteItem: (Int) -> Unit, onShowItem: (Int) -> Unit) {
            binding.productName.text = item.name
            binding.deleteButton.setOnClickListener {
                onDeleteItem(item.productId)
            }
            binding.showButton.setOnClickListener {
                onShowItem(item.productId)
            }
        }
    }
}

private class ProductsDiffCallback : DiffUtil.ItemCallback<ShopProduct>() {

    override fun areItemsTheSame(oldItem: ShopProduct, newItem: ShopProduct): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(
        oldItem: ShopProduct,
        newItem: ShopProduct
    ): Boolean {
        return oldItem == newItem
    }
}
