package com.undef.manosLocalesCernikGaribaldi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.FavoritosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.ProductosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.ApiService
import com.undef.manosLocalesCernikGaribaldi.data.remote.dto.ProductoDTO
import com.undef.manosLocalesCernikGaribaldi.data.remote.dto.toEntity
import com.undef.manosLocalesCernikGaribaldi.utils.NotificationHelper
import javax.inject.Inject

class ProductosRepository @Inject constructor(
    private val api: ApiService,
    private val dao: ProductosDao,
    private val favoritosRepository: FavoritosRepository,
    private val usuariosRepository: UsuariosRepository,
    private val categoriasRepository: CategoriasRepository,
    private val notificationHelper: NotificationHelper
) {
    val allProductos: LiveData<List<ProductoConEmprendimiento>> =
        dao.getAllProductosConEmprendimiento()


    // Instanciamos el helper usando el context de la aplicación


    suspend fun refreshProductos() {
        try {


            val response = this.fetchProductos()
            val apiData = response.map { it.toEntity() }

            // 2. Obtener datos actuales de la Base de Datos (Usa la versión suspend del DAO)
            val existingEntities = dao.getAllProductsSync()

            // 3. Filtrar: ¿Qué es realmente nuevo?
            val nuevos = apiData.filter { apiItem ->
                existingEntities.none { it.Id == apiItem.Id }
            }

            if (nuevos.isNotEmpty()) {
                dao.insertAll(nuevos)
                val userId = usuariosRepository.getSessionId() ?: -1
                nuevos.forEach {
                    val productoConEmprendimiento = dao.getProductoConEmprendimiento(it.Id)
                    if (favoritosRepository.isFavoritoSync(
                            userId,
                            productoConEmprendimiento.emprendimiento.Id
                        )
                    ) {
                        notificationHelper.sendProductNotification(
                            dao.getProductoConEmprendimiento(
                                it.Id
                            ).emprendimiento.name
                        )

                    }
                }
            }

            // 4. Filtrar: ¿Qué ha cambiado de lo que ya teníamos?
            val modificados = apiData.filter { apiItem ->
                val local = existingEntities.find { it.Id == apiItem.Id }
                // Si existe pero el contenido es distinto (gracias al data class)
                local != null && local != apiItem
            }

            //5. Eliminador
            val eliminados = existingEntities.filter { localItem ->
                apiData.none { it.Id == localItem.Id }
            }
            dao.updateAll(modificados)
            dao.deleteAll(eliminados)


        } catch (e: Exception) {
            Log.e("REPO", "Error al sincronizar: ${e.message}")
        }
    }

    suspend fun fetchProductos(): List<ProductoDTO> {
        //antes de traer y guardar un producto la categoria tiene que estar creada
        categoriasRepository.fetchCategorias()
        return api.getProductos()
    }

    suspend fun getProductoById(id: Int): ProductoConEmprendimiento? {
        return dao.getProductById(id)
    }


}