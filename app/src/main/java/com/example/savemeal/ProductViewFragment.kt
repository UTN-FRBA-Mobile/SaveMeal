package com.example.savemeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.savemeal.databinding.FragmentProductViewBinding
import com.example.savemeal.domain.MealDetail
import com.example.savemeal.domain.MealViewModel

class ProductViewFragment : Fragment() {
    private var _binding: FragmentProductViewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductViewBinding.inflate(inflater, container, false)
        val mealId = arguments?.getInt("id")!!
        bindUI(mealId)
        return binding.root
    }

    private fun bindUI(mealId: Int) {
        val meal = viewModel.getMealDetail(mealId)
        bindPicker(meal)
        _binding?.apply {
            expirationDate.text = meal.expiracion
            title.text = meal.nombre
        }
    }

    private fun bindPicker(meal: MealDetail) {
        val np = binding.quantityPicker
        np.minValue = 1
        np.maxValue = meal.disponibles
        np.wrapSelectorWheel = false
        np.value = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonObtainCode.setOnClickListener {

            val dialog = CodeDialogFragment()
            dialog.show(parentFragmentManager, "No se que es el tag")
        }
    }
}