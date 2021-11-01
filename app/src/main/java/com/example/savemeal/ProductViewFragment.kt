package com.example.savemeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.savemeal.databinding.FragmentProductViewBinding

class ProductViewFragment : Fragment() {
    private var _binding: FragmentProductViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductViewBinding.inflate(inflater, container, false)
        val np = binding.quantityPicker
        np.minValue = 1
        np.maxValue = 20
        np.wrapSelectorWheel = false
        np.value = 1
        return binding.root
    }
}