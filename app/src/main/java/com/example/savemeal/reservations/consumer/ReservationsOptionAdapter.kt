package com.example.savemeal.reservations.consumer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.R
import com.example.savemeal.databinding.ReservationsOptionItemBinding
import com.example.savemeal.domain.reservation.ReservationOption

class ReservationsOptionAdapter :
    ListAdapter<ReservationOption, RecyclerView.ViewHolder>(ReservationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReservationOptionViewHolder(
            ReservationsOptionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservOption = getItem(position)
        (holder as ReservationOptionViewHolder).bind(reservOption)

        val bundle = bundleOf("reservationId" to reservOption.reservationId)
        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_reservationsFragment_to_reservationDetailFragment,
                bundle
            )
        )
    }

    class ReservationOptionViewHolder(
        val binding: ReservationsOptionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReservationOption) {
            binding.reservationName.text = item.name
            binding.reservationStatus.text = item.status
        }
    }
}

