package com.junior.testepi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.junior.testepi.databinding.ActivityCadastroBinding
import com.junior.testepi.model.Cadastro
import com.junior.testepi.model.Postagem
import com.junior.testepi.model.Usuario
import com.junior.testepi.util.Validator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var mainViewModel: MainViewModel
    private var usuarioSelecionado: Cadastro? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnPronto.setOnClickListener {
            if (!Validator.validarNome(binding.editNome.text.toString())) {
                binding.editNome.error = "Prencha o nome corretamente"
                binding.editNome.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.validarIdade(binding.editNasc.text.toString())) {
                binding.editNasc.error = "Verique a idade"
                binding.editNasc.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.validarEmail(binding.editEmail.text.toString())) {
                binding.editEmail.error = "Prencha o email completo"
                binding.editEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.validarSenha(binding.editSenha.text.toString())) {
                binding.editSenha.error = "Prencha o senha de acesso"
                binding.editSenha.requestFocus()
                return@setOnClickListener
            }
            inserirNoBanco()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


}
    fun verifiCampos(
        nome: String,
        dataNasc: String,
        email: String,
        genero: String,
        senha: String
    ): Boolean {
        return !(nome == "" || dataNasc == "" || email == "" || genero == "" || senha == "")
    }

    fun inserirNoBanco() {
        val nome = binding.editNome.text.toString()
        val dataNasc = binding.editNasc.text.toString()
        val email = binding.editEmail.text.toString()
        val genero = binding.editGenero.text.toString()
        val senha = binding.editSenha.text.toString()

        if (verifiCampos(nome, dataNasc, email, genero, senha)) {
            val cadastro = Cadastro(0, nome, dataNasc, email, genero, senha)
            mainViewModel.userCadastro(cadastro)
            Toast.makeText(this, "Cadastro feito!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this, "Verifique os campos!", Toast.LENGTH_LONG).show()
        }
    }
}
