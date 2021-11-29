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
        showSpinner()
        listViewModel.viewModelScope.launch {
            listViewModel.getUserReservations().observe(viewLifecycleOwner) { meals ->
                adapter.submitList(meals)
                hideSpinner()
            }
        }
    }

    private fun hideSpinner() {
        binding.spinner.visibility = View.GONE
        binding.reservasRecycler.visibility = View.VISIBLE
    }

    private fun showSpinner() {
        binding.spinner.visibility = View.VISIBLE
        binding.reservasRecycler.visibility = View.GONE
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}