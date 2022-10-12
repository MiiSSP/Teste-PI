package com.junior.testepi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.junior.testepi.databinding.FragmentProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        binding = FragmentProfileBinding.inflate(layoutInflater)


    }

}