package com.junior.testepi

import android.util.Log
import androidx.lifecycle.*
import com.junior.testepi.api.Repository
import com.junior.testepi.model.Postagem
import com.junior.testepi.model.Tema
import com.junior.testepi.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository)
    : ViewModel() {
    var postagemSelecionada: Postagem? = null
    private val _myTemaResponse = MutableLiveData<Response<List<Tema>>>()
    private val _userResponse = MutableLiveData<Response<List<Usuario>>>()
    val myTemaResponse: LiveData<Response<List<Tema>>> = _myTemaResponse
    val userResponse: LiveData<Response<List<Usuario>>> = _userResponse
    val dataSelecionada = MutableLiveData<LocalDate>()

//    init {
//        listTema()
//    }



    fun listTema(){
        viewModelScope.launch {

            try {
                val response = repository.listTema()
                _myTemaResponse.value = response
            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }

        }
    }
    fun addPost(postagem: Postagem){
        viewModelScope.launch {
            try {
                val response = repository.addPost(postagem)
                Log.d("jsaid", "jodasjod")

            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }
        }
    }
    fun upDatePostagem(postagem: Postagem){
        viewModelScope.launch {
            try {
              repository.upDatePostagem(postagem)
                Log.d("jsaid", "jodasjod")
            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }
        }
    }
}
