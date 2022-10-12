package com.junior.testepi.api

import com.junior.testepi.model.Cadastro
import com.junior.testepi.model.Postagem
import com.junior.testepi.model.Tema
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("cadastro")
    suspend fun userCadastro(
        @Body cadastro: Cadastro
    ): Response<Cadastro>

    @GET("cadastro")
    suspend fun verfifyLogin(email: String, senha: String): Response<List<Cadastro>>

    @GET("tema")
    suspend fun listTema():Response<List<Tema>>

    @POST("postagem")
    suspend fun addPost(
        @Body postagem: Postagem
    ): Response<Postagem>

    @GET("postagem")
    suspend fun listPostagem(): Response<List<Postagem>>

    @PUT("postagem")
    suspend fun upDatePost(
        @Body postagem: Postagem
    ): Response<Postagem>

    @DELETE("postagem/{id}")
    suspend fun deletarPostagem(
        @Path("id") id: Long
    ): Response<Postagem>

}