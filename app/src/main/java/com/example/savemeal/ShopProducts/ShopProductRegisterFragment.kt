package com.example.savemeal.ShopProducts

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemeal.CodeDialogFragment
import com.example.savemeal.R
import com.example.savemeal.databinding.FragmentProductRegisterBinding
import com.example.savemeal.databinding.FragmentShopProductsBinding
import com.example.savemeal.domain.product.ShopProductDetail
import com.example.savemeal.domain.product.ShopProductViewModel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class ShopProductRegisterFragment : Fragment() {
    private var _binding: FragmentProductRegisterBinding? = null
    private val binding get() = _binding!!
    val REQUEST_IMAGE_CAPTURE = 1
    private val viewModelShop: ShopProductViewModel by viewModels()

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
                Toast.makeText(context, "Por favor, rellene los campos vacíos", Toast.LENGTH_SHORT).show()
            }else{
                viewModelShop.viewModelScope.launch {
                    var shopProduct = ShopProductDetail(0,
                        binding.nameTextInput.text.toString(),
                        binding.detailsTextInput.text.toString(),
                        binding.expirationTextInput.text.toString(),
                    1,
                        Integer.parseInt(binding.portionsTextInput.text.toString()),
                        1,
                        bitMapToString(binding.productPhoto.drawable.toBitmap()))
                    viewModelShop.createProduct(shopProduct)
                    fragmentManager?.popBackStack()
                    Toast.makeText(context, "Su producto ha sido agregado", Toast.LENGTH_SHORT).show()
                }

            }
        }
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