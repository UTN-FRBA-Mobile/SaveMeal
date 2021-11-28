package com.example.savemeal.active_deliveries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.databinding.FragmentActiveDeliveriesBinding
import com.example.savemeal.domain.reservation.ReservationListViewModel
import kotlinx.coroutines.launch

class ActiveDeliveriesFragment : Fragment() {
    private var _binding: FragmentActiveDeliveriesBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ReservationListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActiveDeliveriesBinding.inflate(inflater, container, false)

        binding.entregasRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ActiveDeliveriesAdapter()
        binding.entregasRecyclerView.adapter = adapter

        subscribe(adapter)
        return binding.root
    }

    private fun subscribe(adapter: ActiveDeliveriesAdapter) {
        listViewModel.viewModelScope.launch {
            listViewModel.getBusinessReservations().observe(viewLifecycleOwner) { meals ->
                adapter.submitList(meals)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}