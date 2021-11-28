package com.example.savemeal.ShopProducts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.R
import com.example.savemeal.databinding.FragmentShopProductsBinding

class ShopProductsFragment : Fragment() {
    private var _binding: FragmentShopProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopProductsBinding.inflate(inflater, container, false)

        binding.productsRecycler.layoutManager = LinearLayoutManager(context)
        binding.productsRecycler.adapter = ShopProductsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener{
            val action = R.id.action_shopProductsFragment_to_shopProductRegisterFragment
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}