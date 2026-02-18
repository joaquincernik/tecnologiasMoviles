package com.undef.manosLocalesCernikGaribaldi.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    val dao = MyApplication.myAppDatabase.productosDao()
    val repository = ProductosRepository(RetrofitClient.apiService, dao)
    val listaProductos: LiveData<List<ProductoConEmprendimiento>> = repository.allProductos
    init {
        viewModelScope.launch(Dispatchers.IO) { repository.refreshProductos() }

    }

}