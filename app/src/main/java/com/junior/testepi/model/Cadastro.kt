package com.junior.testepi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Cadastro(
    val id: Long,
    val nome: String,
    val dataNasc: String,
    val email: String,
    val genero: String,
    val senha: String
) {


}