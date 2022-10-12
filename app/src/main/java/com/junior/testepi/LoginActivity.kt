package com.junior.testepi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.junior.testepi.databinding.ActivityLoginBinding
import com.junior.testepi.model.Cadastro
import com.junior.testepi.util.Validator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var usuarioSelecionado: Cadastro? = null
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)
        carregarDados()
        binding.btnLogin.setOnClickListener {
            if (!Validator.validarEmail(binding.verEmail.text.toString())) {
                binding.verEmail.error = "Preencha com o email de acesso"
                binding.verEmail.requestFocus()
            }
            if (!Validator.validarSenha(binding.verSenha.text.toString())) {
                binding.verSenha.error = "Prencha o senha de acesso"
                binding.verSenha.requestFocus()
            }

            validarLogin()
        }
        binding.btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
    fun validarLogin() {
        val email = binding.verEmail.text.toString()
        val senha = binding.verSenha.text.toString()

        if (email == usuarioSelecionado?.email && senha == usuarioSelecionado?.senha) {
            mainViewModel.verifyCadastro(email, senha)
            Toast.makeText(this, "Certo!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_LONG).show()
        }

    }

    private fun carregarDados(){
        usuarioSelecionado = mainViewModel.usuarioSelecionado
        if (usuarioSelecionado != null){
            binding.verEmail.setText(usuarioSelecionado?.email)
            binding.verSenha.setText(usuarioSelecionado?.senha)
        }
    }
}



