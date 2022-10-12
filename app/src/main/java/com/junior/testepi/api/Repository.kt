package com.junior.testepi.api

import com.junior.testepi.model.Cadastro
import com.junior.testepi.model.Postagem
import com.junior.testepi.model.Tema
import com.junior.testepi.model.Usuario
import retrofit2.Response

class Repository {


    suspend fun userCadastro(cadastro: Cadastro): Response<Cadastro>{
        return RetrofitInstance.api.userCadastro(cadastro)
    }
    suspend fun verifyLogin(email: String, senha: String): Response<List<Cadastro>>{
        return RetrofitInstance.api.verfifyLogin(email, senha)
    }
    suspend fun listTema(): Response<List<Tema>>{
        return RetrofitInstance.api.listTema()
    }
    suspend fun listPostagem(): Response<List<Postagem>>{
        return RetrofitInstance.api.listPostagem()
    }

    suspend fun addPost(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.addPost(postagem)
    }
    suspend fun upDatePostagem(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.upDatePost(postagem)
    }

    suspend fun deletarPostagem(id: Long): Response<Postagem>{
        return RetrofitInstance.api.deletarPostagem(id)
    }


}

