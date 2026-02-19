package com.undef.manosLocalesCernikGaribaldi.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity
import com.undef.manosLocalesCernikGaribaldi.data.repository.UsuariosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: UsuariosRepository
) : ViewModel() {

    //lo que la activity observara
    private val _uiState = MutableLiveData<LoginUiState>() //_ porque es privado
    val uiState: LiveData<LoginUiState> get() = _uiState //cuando acceda el valor de uistate obtengo el de _uistate

    fun checkSession() : Boolean{
        return repository.checkSession()
    }

    fun login(email: String, password: String) {
        //validaciones simple
        if (email.isEmpty() || password.isEmpty()) {
            _uiState.value = LoginUiState(errorMessage = "Completa todos los campos")
        }

        //corrutina
        viewModelScope.launch {
            _uiState.value = LoginUiState(isLoading = true)

            //busco en los usuarios
            val user = repository.checkUser(email, password)

            if (user != null) {
                _uiState.value = LoginUiState(isSuccess = true)
                //aca guardamos en la shared preference
                repository.saveSession(email, user.Id)
            } else {
                _uiState.value = LoginUiState(errorMessage = "Usuario o contraseña incorrectos")

            }

        }

    }
    /*
    * Aca definis clases o funciones de negocio que vos queres separar de la parte visual digamos.
    * No podes definir en un composable un acceso a una base de datos, esta responsabilidad esta en otro lado.
    * Ademas por cuestiones arquitectonicas de android esto no funciona bien, vos darias vuelta la pantalla del celu
    * y debido al ciclo de vida de la activity cada vez que pase esto se reiniciaria la activity y pegarias de nuevo
    * en el endpoint, en la base de datos y esto lo hace sumamente ineficiente. La idea de esto es separar responsabilidades
    * y que la activity dedicada a dibujar la pantalla solo consuma de aca.
    * Ejemplo de como acceder a una bd desde un view model.
    * YA SE QUE ESTA MAL VOY A PONER EL EJEMPLO DE LA PARTE DE EMPRENDIMIENTOS.
    * fun saveProductos(){
    * viewModelScope.launch(Dispatchers.IO){
        * MyApplication.MyAppRoomDatabase.productDAO
        *   .insertProduct(
        *       ProductEntity(
        *           campos a llenar
        *       )
        *   )
        * }
        * }
        *
        * obvio lo podria tambien por parametro en vez de armar la clase ahi. Seria mejor hacerlo asi mas vale.
        *
        * MINUTO 50:35
        *
        * los metodos de viewmodel no pueden ser privados porque tengo que acceder desde afuera.
    *todos los METODOs QUE YO vaya a  LLAMar DE UNA CORRUTINA TIENEn  QUE TENER SUSPEND. osea suspend fun funcion() ==> esto hace que no bloquee la ui
    * Como llamo desde la activity a los metodos de view model?
    * tengo que en la activity hacer lo siguiente:
    *
    * private val loginViewModel:LoginViewModel by viewModels() ==>  no es mejor usar inyector de dependencias?
    * en el caso de que sea compose, parece que se hace asi:
    *     @Composable
    fun LoginScreen(viewModel: LoginViewModel = viewModel()) { ... }

    *¿Por qué usar un Inyector de Dependencias (como Hilt o Koin)?
El problema de by viewModels() a secas es que el ViewModel solo puede tener un constructor vacío.
Si tu LoginViewModel necesita un UserRepository (que a su vez necesita un ApiService y una Database), no podés pasárselo fácilmente. Ahí entra el inyector:
1.
Sin Inyector: Tenés que crear un ViewModelFactory manual (es un código bastante molesto y repetitivo).
2.
Con Inyector (Hilt): Solo ponés una anotación y el sistema se encarga de "fabricar" todo el árbol de dependencias por vos.
    *
    *    @HiltViewModel
    class LoginViewModel @Inject constructor(
        private val repository: UserRepository // El inyector se encarga de esto
    ) : ViewModel() { ... }

    *
    *
    *
    *
    * ahora hicimos un insert. Pero si quiero recuperar la informacion? osea un select?
    *
    *
    *
    * podemos hacer esto.
    *
    * PARA UI COMUN.
    *
    * fun loadColors(){
    *   viewModelScope.launch(Dispatcher.IO){
    *   MyApplication.myApplication.colorDao.getAllColors()       esto es un ejemplito nomas
    * }
    * }
    *
    *
    * livedata ==> es una variable a la que puedo conectarme (patron de diseño observer), cuando se actualiza esta variable puedo reaccionar en consecuencoa.
    * El view model trae la data de la bd y se la asigna a esta variable. La ui observa el valor de livedata y reacciona en consecuencia
    *
    * private var color = MutableLiveData<List<ColorData>>() = ColorData es lo que devuelve loadColors.
    *
    *
    * tenemos que hacer un implementation en el gradle para que funcione esto se llama androidx.compose.runtime:runtime-livedata:2.9.0 o algo asi.

    *androidx.lifecycle:lifecycle-livedata-ktx:2.9.0 ==> este tambien parece.
    *
    * MINUTO 1:02:02 se pone a explicar como implementar esto
    * tuvo error con setValue, se soluciona con postValue
    *
    *
    * clase 19 explica como hacer lo del observable pero con compose.
    *
    * */


}