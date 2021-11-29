package com.example.savemeal.meals.shop

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.R
import com.example.savemeal.databinding.FragmentShopProductsBinding
import com.example.savemeal.domain.meal.ShopMealListViewModel
import kotlinx.coroutines.launch

class ShopMealsFragment : Fragment() {
    private var _binding: FragmentShopProductsBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ShopMealListViewModel by viewModels()
    private lateinit var adapter : ShopMealAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopProductsBinding.inflate(inflater, container, false)

        binding.productsRecycler.layoutManager = LinearLayoutManager(context)

        adapter = ShopMealAdapter({ id -> onDeleteMeal(id) }, { id -> onShowMeal(id) })

        binding.productsRecycler.adapter = adapter
        subscribeMeals()
        return binding.root
    }

    private fun onShowMeal(mealId: Int) {
        val bundle = bundleOf("id" to mealId, "show_buttons" to false)
        val action = R.id.action_shopProductsFragment_to_productViewFragment
        findNavController().navigate(action, bundle)
    }

    private fun onDeleteMeal(mealId: Int) {
        AlertDialog.Builder(context)
            .setMessage("¿Desea eliminar el producto?")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Si") { _, _ ->
                deleteMeal(mealId)
            }
            .show()
    }

    private fun deleteMeal(mealId: Int) {
        listViewModel.viewModelScope.launch {
            listViewModel.deleteMeal(mealId)
            loadMealsInAdapter()
            Toast.makeText(context, "Comida eliminada", Toast.LENGTH_LONG).show()
        }
    }

    private fun subscribeMeals() {
        listViewModel.viewModelScope.launch {
            loadMealsInAdapter()
        }
    }

    private suspend fun loadMealsInAdapter() {
        showSpinner()
        listViewModel.getMeals().observe(viewLifecycleOwner) { meals ->
            adapter.submitList(meals)
            hideSpinner()
        }
    }

    private fun hideSpinner() {
        binding.spinner.visibility = View.GONE
        binding.fab.visibility = View.VISIBLE
        binding.productsRecycler.visibility = View.VISIBLE
    }

    private fun showSpinner() {
        binding.spinner.visibility = View.VISIBLE
        binding.fab.visibility = View.GONE
        binding.productsRecycler.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            val action = R.id.action_shopProductsFragment_to_shopProductRegisterFragment
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}