package com.example.savemeal.reservations.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.databinding.ActiveDeliveryItemBinding
import com.example.savemeal.domain.reservation.ReservationOption
import com.example.savemeal.reservations.consumer.ReservationDiffCallback

class ShopActiveReservationAdapter(
    private val onDeleteItem: (Int) -> Unit,
    private val onDeliveredItem: (Int) -> Unit
) : ListAdapter<ReservationOption, RecyclerView.ViewHolder>(ReservationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShopActiveReservationViewHolder(
            ActiveDeliveryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val activeReservation = getItem(position)
        (holder as ShopActiveReservationViewHolder).bind(activeReservation, onDeleteItem, onDeliveredItem)
    }

    class ShopActiveReservationViewHolder(
        val binding: ActiveDeliveryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ReservationOption,
            onDeleteItem: (Int) -> Unit,
            onDeliveredItem: (Int) -> Unit
        ) {
            binding.productName.text = item.name
            binding.deleteButton.setOnClickListener {
                onDeleteItem(item.reservationId)
            }
            binding.checkButton.setOnClickListener {
                onDeliveredItem(item.reservationId)
            }
        }
    }
}

