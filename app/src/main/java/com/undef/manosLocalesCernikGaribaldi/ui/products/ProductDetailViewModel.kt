package com.undef.manosLocalesCernikGaribaldi.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductosRepository
) : ViewModel() {

    val _producto = MutableLiveData<ProductoConEmprendimiento>()
    val producto: LiveData<ProductoConEmprendimiento> = _producto

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _producto.value = repository.getProductoById(productId)

        }
    }


}