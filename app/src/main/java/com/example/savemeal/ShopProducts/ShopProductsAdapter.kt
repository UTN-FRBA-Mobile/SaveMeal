package com.example.savemeal.ShopProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.databinding.ShopProductItemBinding

class ShopProductsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val products = listOf(
        ShopProduct("Opcion 1"),
        ShopProduct("Opcion 2"),
        ShopProduct("Opcion 3")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShopProductViewHolder(
            ShopProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shopProduct = products[position]
        (holder as ShopProductViewHolder).bind(shopProduct)

        // TODO onclick listeners a los botones de editar y eliminat
//        holder.itemView.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_reservationsFragment_to_reservationDetailFragment)
//        )
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    class ShopProductViewHolder(
        val binding: ShopProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShopProduct) {
            binding.productName.text = item.name
        }
    }
}
