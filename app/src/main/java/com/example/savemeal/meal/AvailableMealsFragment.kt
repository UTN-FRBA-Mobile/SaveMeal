package com.example.savemeal.meal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.databinding.FragmentAvailableMealsBinding

class AvailableMealsFragment : Fragment() {
    private var _binding: FragmentAvailableMealsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvailableMealsBinding.inflate(inflater, container, false)

        binding.avilableMealsOptions.layoutManager = LinearLayoutManager(context)
        binding.avilableMealsOptions.adapter = MealOptionAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}