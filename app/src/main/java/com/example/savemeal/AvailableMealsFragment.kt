package com.example.savemeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.savemeal.databinding.FragmentAvailableMealsBinding

class AvailableMealsFragment : Fragment() {
    private var _binding: FragmentAvailableMealsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAvailableMealsBinding.inflate(inflater, container, false)
        return binding.root
    }
}