package com.example.savemeal.domain.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savemeal.ShopProductDI
import com.example.savemeal.ShopProducts.ShopProduct

class ShopProductListViewModel: ViewModel() {
    private val shopProductRepository: ShopProductRepository = ShopProductDI.provideShopProductRepository()

    suspend fun getProducts(): LiveData<List<ShopProduct>> {
        val mutableLiveData = MutableLiveData<List<ShopProduct>>()
        mutableLiveData.value = shopProductRepository.getShopProducts()
        return mutableLiveData
    }
}