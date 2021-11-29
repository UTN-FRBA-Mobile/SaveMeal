package com.example.savemeal.ShopProducts

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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.R
import com.example.savemeal.databinding.FragmentShopProductsBinding
import com.example.savemeal.domain.product.ShopProductListViewModel
import kotlinx.coroutines.launch

class ShopProductsFragment : Fragment() {
    private var _binding: FragmentShopProductsBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ShopProductListViewModel by viewModels()
    private lateinit var adapter : ShopProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopProductsBinding.inflate(inflater, container, false)

        binding.productsRecycler.layoutManager = LinearLayoutManager(context)

        adapter = ShopProductsAdapter({ id -> onDeleteProduct(id) }, { id -> onShowProduct(id) })

        binding.productsRecycler.adapter = adapter
        subscribeProducts()
        return binding.root
    }

    private fun onShowProduct(productId: Int) {
        val bundle = bundleOf("id" to productId, "show_buttons" to false)
        val action = R.id.action_shopProductsFragment_to_productViewFragment
        findNavController().navigate(action, bundle)
    }

    private fun onDeleteProduct(productId: Int) {
        AlertDialog.Builder(context)
            .setMessage("¿Desea eliminar el producto?")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Si") { _, _ ->
                deleteProduct(productId)
            }
            .show()
    }

    private fun deleteProduct(productId: Int) {
        listViewModel.viewModelScope.launch {
            listViewModel.deleteProduct(productId)
            loadProductsInAdapter()
            Toast.makeText(context, "Producto eliminado", Toast.LENGTH_LONG).show()
        }
    }

    private fun subscribeProducts() {
        listViewModel.viewModelScope.launch {
            loadProductsInAdapter()
        }
    }

    private suspend fun loadProductsInAdapter() {
        showSpinner()
        listViewModel.getProducts().observe(viewLifecycleOwner) { products ->
            adapter.submitList(products)
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