package com.junior.testepi.model

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Usuario(
     val id: Long,
     val nickname:  String,
) {
}