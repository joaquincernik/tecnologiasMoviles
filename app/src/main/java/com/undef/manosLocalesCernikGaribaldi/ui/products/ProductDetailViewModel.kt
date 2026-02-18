package com.undef.manosLocalesCernikGaribaldi.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel(){
    val dao = MyApplication.myAppDatabase.productosDao()
    val repository = ProductosRepository(RetrofitClient.apiService, dao)

    val _producto = MutableLiveData<ProductoConEmprendimiento>()
    val producto: LiveData<ProductoConEmprendimiento> = _producto

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _producto.value = repository.getProductoById(productId)

        }
    }




}