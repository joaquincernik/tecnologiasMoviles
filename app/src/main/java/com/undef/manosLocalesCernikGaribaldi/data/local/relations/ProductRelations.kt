package com.undef.manosLocalesCernikGaribaldi.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.CategoriasEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.FavoritosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity


/*
* Para no complicar las entidades (que deben ser simples), creamos el archivo ProductRelations.kt.
Cuando quieres obtener un "Producto con su Emprendimiento", Room hace lo siguiente tras bambalinas:
1.
Busca el producto.
2.
Mira el emprendimientoId.
3.
Va a la tabla de emprendimientos, busca ese ID y te lo devuelve todo "empaquetado" en la clase ProductoConEmprendimiento.*/




// 1. Producto con su Emprendimiento (Relación 1:1 desde la perspectiva del producto)
data class ProductoConEmprendimiento(
    @Embedded val producto: ProductosEntity,
    @Relation(
        parentColumn = "emprendimientoId",
        entityColumn = "Id"
    )
    val emprendimiento: EmprendimientosEntity
)

// 3. Emprendimiento con todos sus Productos (Relación 1:N)
data class EmprendimientoConProductos(
    @Embedded val emprendimiento: EmprendimientosEntity,
    @Relation(
        parentColumn = "Id",
        entityColumn = "emprendimientoId"
    )
    val productos: List<ProductosEntity>
)

data class CategoriaConProductos(
    @Embedded val categoria: CategoriasEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val productos: List<ProductosEntity>
)
// 4. Usuario con sus Productos Favoritos (Relación N:M)
/*data class UsuarioConFavoritos(
    @Embedded val usuario: UsuariosEntity,
    @Relation(
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = FavoritosEntity::class,
            parentColumn = "userId",
            entityColumn = "productId"
        )
    )
    val productosFavoritos: List<ProductosEntity>
)
*/