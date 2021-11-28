package com.example.savemeal.ShopProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.R
import com.example.savemeal.databinding.ShopProductItemBinding
import com.example.savemeal.domain.reservation.ReservationOption

class ShopProductsAdapter : ListAdapter<ShopProduct,RecyclerView.ViewHolder>(ProductsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShopProductViewHolder(
            ShopProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shopProduct = getItem(position)
        (holder as ShopProductViewHolder).bind(shopProduct)

        val bundle = bundleOf("productId" to shopProduct.productId)


        // TODO onclick listeners a los botones de editar y eliminat
//        holder.itemView.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_reservationsFragment_to_reservationDetailFragment)
//        )
    }

    class ShopProductViewHolder(
        val binding: ShopProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShopProduct) {
            binding.productName.text = item.name
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
