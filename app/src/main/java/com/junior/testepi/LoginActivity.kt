package com.junior.testepi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.junior.testepi.databinding.ActivityLoginBinding
import com.junior.testepi.model.Cadastro
import com.junior.testepi.util.Validator
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mainViewModel: MainViewModel
    private var listCadastros = arrayListOf<ArrayList<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)


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
        binding.buttonLoginGoogle.setOnClickListener {
            val loading = LoadingDiaolog(this)
            loading.startLoading()
            val handler = Handler()
            handler.postDelayed(object: Runnable{
                override fun run(){
                    loading.dismiss()
                }
            }, 5000)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }


    fun criarCadastros(){
        listCadastros.add(arrayListOf("Fabiano@gmail.com", "Fabiano"))
        listCadastros.add(arrayListOf("al860270@gmail.com", "Qwertyui"))

    }
    fun validarLogin() {

        criarCadastros()

        val email = binding.verEmail.text.toString().lowercase()
        val senha = binding.verSenha.text.toString().lowercase()
        var entrou = false

        for(n in 0 until listCadastros.size){
            if (email == listCadastros[n][0].lowercase() && senha == listCadastros[n][1].lowercase()) {
                mainViewModel.verifyCadastro(email, senha)
                Toast.makeText(this, "Sucesso ao entrar!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                entrou = true
            }
        }
        if (entrou == false){
            Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_LONG).show()
        }
    }
}



