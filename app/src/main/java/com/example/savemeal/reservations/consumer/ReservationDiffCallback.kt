package com.example.savemeal.reservations.consumer

import androidx.recyclerview.widget.DiffUtil
import com.example.savemeal.domain.reservation.ReservationOption

class ReservationDiffCallback : DiffUtil.ItemCallback<ReservationOption>() {

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