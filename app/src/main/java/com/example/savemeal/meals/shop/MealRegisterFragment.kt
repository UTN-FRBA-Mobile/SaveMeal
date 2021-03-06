package com.example.savemeal.meals.shop

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.savemeal.databinding.FragmentProductRegisterBinding
import com.example.savemeal.domain.meal.ShopMealViewModel
import com.example.savemeal.infrastructure.NewMeal
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class MealRegisterFragment : Fragment() {
    private var _binding: FragmentProductRegisterBinding? = null
    private val binding get() = _binding!!
    val REQUEST_IMAGE_CAPTURE = 1
    private val viewModelShop: ShopMealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCamera.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(context?.packageManager!!)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }

        binding.buttonCreateProduct.setOnClickListener {
            if (binding.nameTextInput.text.isNullOrEmpty() ||
                    binding.detailsTextInput.text.isNullOrEmpty() ||
                    binding.portionsTextInput.text.isNullOrEmpty() ||
                    binding.expirationTextInput.text.isNullOrEmpty() ||
                    binding.productPhoto.drawable==null){
                Toast.makeText(context, "Por favor, rellene los campos vac??os", Toast.LENGTH_SHORT).show()
            }else{
                viewModelShop.viewModelScope.launch {
                    val newMeal = createNewMeal()
                    viewModelShop.createMeal(newMeal)
                    fragmentManager?.popBackStack()
                    Toast.makeText(context, "Su comida ha sido agregada", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun createNewMeal(): NewMeal {
        return NewMeal(nombre = binding.nameTextInput.text.toString(),
        detalle = binding.detailsTextInput.text.toString(),
        expiracion = binding.expirationTextInput.text.toString(),
        porciones = Integer.parseInt(binding.portionsTextInput.text.toString()),
        imagen = bitMapToString(binding.productPhoto.drawable.toBitmap()))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.productPhoto.setImageBitmap(imageBitmap)
        }
    }

    fun bitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}