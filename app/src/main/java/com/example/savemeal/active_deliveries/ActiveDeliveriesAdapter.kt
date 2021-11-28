package com.example.savemeal.active_deliveries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.ShopProducts.ShopProduct
import com.example.savemeal.databinding.ActiveDeliveryItemBinding
import com.example.savemeal.databinding.ShopProductItemBinding
import com.example.savemeal.domain.reservation.ReservationOption

class ActiveDeliveriesAdapter : ListAdapter<ReservationOption, RecyclerView.ViewHolder>(DeliveriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DeliveryViewHolder(
            ActiveDeliveryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val activeReservation = getItem(position)
        (holder as DeliveryViewHolder).bind(activeReservation)

        val bundle = bundleOf("productId" to activeReservation.reservationId)


        // TODO onclick listeners a los botones de editar y eliminat
//        holder.itemView.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_reservationsFragment_to_reservationDetailFragment)
//        )
    }

    class DeliveryViewHolder(
        val binding: ActiveDeliveryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReservationOption) {
            binding.productName.text = item.name
        }
    }
}

private class DeliveriesDiffCallback : DiffUtil.ItemCallback<ReservationOption>() {

    override fun areItemsTheSame(oldItem: ReservationOption, newItem: ReservationOption): Boolean {
        return oldItem.reservationId == newItem.reservationId
    }

    override fun areContentsTheSame(
        oldItem: ReservationOption,
        newItem: ReservationOption
    ): Boolean {
        return oldItem == newItem
    }
}
