package com.junior.testepi

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import com.junior.testepi.api.Repository
import com.junior.testepi.model.Cadastro
import com.junior.testepi.model.Postagem
import com.junior.testepi.model.Tema
import com.junior.testepi.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository)
    : ViewModel() {
    var postagemSelecionada: Postagem? = null
    private val _myTemaResponse = MutableLiveData<Response<List<Tema>>>()
    val myTemaResponse: LiveData<Response<List<Tema>>> = _myTemaResponse
    private val _myPostagemResponse = MutableLiveData<Response<List<Postagem>>>()
    val myPostagemResponse: LiveData<Response<List<Postagem>>> = _myPostagemResponse

    private val _myUserResponse = MutableLiveData<Response<List<Cadastro>>>()
    val myUserResponse: LiveData<Response<List<Cadastro>>> = _myUserResponse

//    init {
//        listTema()
//    }

    fun listPostagem(){
        viewModelScope.launch {
            try {
                val response = repository.listPostagem()
                _myPostagemResponse.value= response
            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }
        }
    }
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
                repository.addPost(postagem)
                Log.d("jsaid", "jodasjod")
                listPostagem()
            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }
        }
    }
    fun upDatePostagem(postagem: Postagem){
        viewModelScope.launch {
            try {
              repository.upDatePostagem(postagem)
                Log.d("Update", "123")
                listPostagem()
            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }
        }
    }

    fun deletarPostagem(id: Long){
        viewModelScope.launch {
            try {
                repository.deletarPostagem(id)
                listPostagem()
            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }
        }
    }

    fun userCadastro(cadastro: Cadastro){
        viewModelScope.launch {
            try {
                repository.userCadastro(cadastro)
            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }
        }
    }

    fun verifyCadastro(email: String, senha: String){
        viewModelScope.launch {
            try {
                repository.verifyLogin(email, senha)

            }catch (e: Exception){
                Log.d("Erro:/", e.message.toString())
            }
        }
    }
}
