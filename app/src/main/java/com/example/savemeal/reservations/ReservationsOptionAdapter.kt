package com.example.savemeal.reservations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.R
import com.example.savemeal.databinding.ReservationsOptionItemBinding

class ReservationsOptionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val options = listOf(
        ReservationOption("Opcion 1","Activa"),
        ReservationOption("Opcion 2","Entregada"),
        ReservationOption("Opcion 3", "Activa")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReservationOptionViewHolder(
            ReservationsOptionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservOption = options[position]
        (holder as ReservationOptionViewHolder).bind(reservOption)

        //holder.itemView.setOnClickListener(
            //Navigation.createNavigateOnClickListener()
        //)
    }

    override fun getItemCount(): Int {
        return options.count()
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
