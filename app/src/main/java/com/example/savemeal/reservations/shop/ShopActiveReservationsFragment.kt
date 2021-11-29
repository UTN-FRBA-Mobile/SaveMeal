package com.example.savemeal.reservations.shop

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.databinding.FragmentActiveDeliveriesBinding
import com.example.savemeal.domain.reservation.ReservationListViewModel
import kotlinx.coroutines.launch

class ShopActiveReservationsFragment : Fragment() {
    private var _binding: FragmentActiveDeliveriesBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ReservationListViewModel by viewModels()
    private lateinit var adapterShop: ShopActiveReservationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActiveDeliveriesBinding.inflate(inflater, container, false)

        binding.entregasRecyclerView.layoutManager = LinearLayoutManager(context)
        adapterShop = ShopActiveReservationAdapter({ id -> onDeleteDelivery(id) }, { id -> onDelivered(id) })
        binding.entregasRecyclerView.adapter = adapterShop

        subscribe()
        return binding.root
    }

    private fun onDelivered(id: Int) {
        AlertDialog.Builder(context)
            .setMessage("¿La reserva fue entregada?")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Si") { _, _ ->
                delivered(id)
            }
            .show()
    }

    private fun delivered(id: Int) {
        listViewModel.viewModelScope.launch {
            listViewModel.markDeliveredProduct(id)
            loadDeliveriesInAdapter()
            Toast.makeText(context, "Entrega eliminada", Toast.LENGTH_LONG).show()
        }
    }

    private fun onDeleteDelivery(id: Int) {
        AlertDialog.Builder(context)
            .setMessage("¿Desea eliminar la entrega?")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Si") { _, _ ->
                deleteDelivery(id)
            }
            .show()
    }

    private fun deleteDelivery(id: Int) {
        listViewModel.viewModelScope.launch {
            listViewModel.deleteProduct(id)
            loadDeliveriesInAdapter()
            Toast.makeText(context, "Entrega eliminada", Toast.LENGTH_LONG).show()
        }
    }

    private fun subscribe() {
        showSpinner()
        listViewModel.viewModelScope.launch {
            loadDeliveriesInAdapter()
            hideSpinner()
        }
    }

    private fun hideSpinner() {
        binding.spinner.visibility = View.GONE
        binding.entregasRecyclerView.visibility = View.VISIBLE
    }

    private fun showSpinner() {
        binding.spinner.visibility = View.VISIBLE
        binding.entregasRecyclerView.visibility = View.GONE
    }

    private suspend fun loadDeliveriesInAdapter() {
        listViewModel.getBusinessReservations().observe(viewLifecycleOwner) { meals ->
            adapterShop.submitList(meals)
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}