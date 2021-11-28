package com.example.savemeal.reservations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.databinding.FragmentReservationsBinding
import com.example.savemeal.domain.reservation.ReservationListViewModel
import kotlinx.coroutines.launch

class ReservationsFragment : Fragment() {
    private var _binding: FragmentReservationsBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ReservationListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationsBinding.inflate(inflater, container, false)

        binding.reservasRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = ReservationsOptionAdapter()
        binding.reservasRecycler.adapter = adapter

        subscribe(adapter)
        return binding.root
    }

    private fun subscribe(adapter: ReservationsOptionAdapter) {
        listViewModel.viewModelScope.launch {
            listViewModel.getReservations().observe(viewLifecycleOwner) { meals ->
                adapter.submitList(meals)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}