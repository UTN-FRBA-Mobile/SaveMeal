package com.example.savemeal.meals.consumer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.databinding.FragmentAvailableMealsBinding
import com.example.savemeal.domain.meal.MealListViewModel
import kotlinx.coroutines.launch

class AvailableMealsFragment : Fragment() {
    private var _binding: FragmentAvailableMealsBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: MealListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvailableMealsBinding.inflate(inflater, container, false)

        binding.avilableMealsOptions.layoutManager = LinearLayoutManager(context)
        val adapter = AvailableMealAdapter()
        binding.avilableMealsOptions.adapter = adapter

        subscribe(adapter)
        return binding.root
    }

    private fun subscribe(adapter: AvailableMealAdapter) {
        showSpinner()
        listViewModel.viewModelScope.launch {
            listViewModel.getAvailableMeals().observe(viewLifecycleOwner) { meals ->
                adapter.submitList(meals)
                hideSpinner()
            }
        }
    }

    private fun hideSpinner() {
        binding.spinner.visibility = View.GONE
        binding.avilableMealsOptions.visibility = View.VISIBLE
    }

    private fun showSpinner() {
        binding.spinner.visibility = View.VISIBLE
        binding.avilableMealsOptions.visibility = View.GONE
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}