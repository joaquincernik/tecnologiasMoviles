package com.undef.manosLocalesCernikGaribaldi.ui.categorias

import android.util.Log
import androidx.activity.result.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.CategoriaConProductos
import com.undef.manosLocalesCernikGaribaldi.data.repository.CategoriasRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriaDetailViewModel @Inject constructor(
    private val repository: CategoriasRepository,
    private val productosRepository: ProductosRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<CategoriaConProductos?>()
    val uiState: LiveData<CategoriaConProductos?> = _uiState


    fun loadCategoria(categoriaId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            try{
                productosRepository.refreshProductos()
                val resultado = repository.getCategoriaConProductos(categoriaId)
                _uiState.postValue(resultado)
            }catch (e: Exception){
                // Manejar el error
                Log.e("DETAIL_VM", "Error cargando producto por categoria: ${e.message}")

            }
        }
    }
}