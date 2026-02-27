package com.undef.manosLocalesCernikGaribaldi.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.repository.CategoriasRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductosRepository,
    private val categoriasRepository: CategoriasRepository
) : ViewModel() {

    val listaProductos: LiveData<List<ProductoConEmprendimiento>> = repository.allProductos
    init {
        viewModelScope.launch(Dispatchers.IO) {
           // categoriasRepository.refreshCategorias()
            repository.refreshProductos()
        }

    }

}