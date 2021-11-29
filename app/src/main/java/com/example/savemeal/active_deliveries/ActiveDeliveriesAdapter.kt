package com.example.savemeal.active_deliveries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.databinding.ActiveDeliveryItemBinding
import com.example.savemeal.domain.reservation.ReservationOption

class ActiveDeliveriesAdapter(
    private val onDeleteItem: (Int) -> Unit,
    private val onDeliveredItem: (Int) -> Unit
) : ListAdapter<ReservationOption, RecyclerView.ViewHolder>(DeliveriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DeliveryViewHolder(
            ActiveDeliveryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val activeReservation = getItem(position)
        (holder as DeliveryViewHolder).bind(activeReservation, onDeleteItem, onDeliveredItem)
    }

    class DeliveryViewHolder(
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
