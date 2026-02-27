package com.undef.manosLocalesCernikGaribaldi.ui.categorias

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.CategoriasEntity
import com.undef.manosLocalesCernikGaribaldi.data.repository.CategoriasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriasViewModel @Inject constructor(
    private val repository: CategoriasRepository
) : ViewModel() {
    val categorias: LiveData<List<CategoriasEntity>> = repository.allCategories

    init {
        viewModelScope.launch (context = Dispatchers.IO){
            repository.refreshCategorias()
        }
    }
}