package com.example.savemeal.domain.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savemeal.MealDI
import com.example.savemeal.ShopProductDI
import com.example.savemeal.ShopProducts.ShopProduct
import com.example.savemeal.domain.meal.MealOption
import com.example.savemeal.domain.meal.MealRepository

class ShopProductListViewModel: ViewModel() {
    private val shopProductRepository: MealRepository = MealDI.provideMealRepository()

    suspend fun getProducts(): LiveData<List<MealOption>> {
        val mutableLiveData = MutableLiveData<List<MealOption>>()
        mutableLiveData.value = shopProductRepository.getShopProducts()
        return mutableLiveData
    }

    suspend fun deleteProduct(productId: Int) {
        shopProductRepository.deleteProduct(productId)
    }
}