package com.example.savemeal.meals

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.savemeal.R
import com.example.savemeal.databinding.FragmentMealDetailBinding
import com.example.savemeal.domain.meal.MealDetail
import com.example.savemeal.domain.meal.MealViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import android.graphics.BitmapFactory
import android.util.Base64
import com.google.android.gms.common.util.Base64Utils


class MealDetailFragment : Fragment() {
    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MealViewModel by viewModels()
    private lateinit var meal: MealDetail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        val mealId = arguments?.getInt("id")!!
        bindUI(mealId)
        return binding.root
    }

    private fun bindUI(mealId: Int) {
        meal = viewModel.getMealDetail(mealId)
        _binding?.apply {
            expirationDate.text = meal.expiracion
            title.text = meal.nombre
            businessHours.text = meal.business.businessHours
            businessAddress.text = meal.business.address
            businessName.text = meal.business.businessName
            availables.text = meal.disponibles.toString()
            detail.text = meal.detalle

            val imageAsBytes: ByteArray = Base64Utils.decode(meal.imagen)
            photo.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))

            var img = "data:image/jpeg;base64," + meal.imagen

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBookMeal.setOnClickListener {
            showConfirmationPopUp()
        }
    }

    private fun showConfirmationPopUp() {
        AlertDialog.Builder(context)
            .setMessage("¿Desea reservar esta comida?")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Si") { _, _ ->
                makeReservation()
            }
            .show()
    }

    fun removeNewLine(string: String): String{
        return string.replace("\n","")
    }

    private fun makeReservation() {
        viewModel.viewModelScope.launch {
            viewModel.makeReservation(meal)
            val action = R.id.action_productViewFragment_to_availableMealsFragment
            findNavController().navigate(action)
            Toast.makeText(context, "Reserva creada", Toast.LENGTH_LONG).show()

        }
    }
}
