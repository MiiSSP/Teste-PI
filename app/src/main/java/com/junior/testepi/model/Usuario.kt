package com.junior.testepi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Usuario(
     val id: Long,
     val email: String,
     val senha: String,
//    val postagem: List<Postagem>
) {
}