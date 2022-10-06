package com.junior.testepi


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.junior.testepi.databinding.ActivityCadastroBinding
import com.junior.testepi.fragment.DatePicker
import com.junior.testepi.fragment.TimerPickerListener
import java.time.LocalDate
import java.util.*

class CadastroActivity : AppCompatActivity(), TimerPickerListener {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPronto.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnVoltar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.textDate.setOnClickListener{
           val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val popUp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, year, monthofYear, dayOfMonth ->

                val months= monthofYear+1
                binding.textDate.setText("$dayOfMonth/$months/$year")
            }, year, month, day)
                popUp.show()
        }



    }
//    override fun onDateSelected(date: LocalDate){
//        mainViewModel.dataSelecionada.value = date
//    }

}