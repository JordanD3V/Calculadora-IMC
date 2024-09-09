package com.example.calculadoraimc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultsActivity : AppCompatActivity() {

    private lateinit var textInfoPeso: TextView
    private lateinit var textInfoAltura: TextView
    private lateinit var textInfoDiagnostico: TextView
    private lateinit var buttonNovamente: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textInfoAltura = findViewById(R.id.txt_info_altura)
        textInfoPeso = findViewById(R.id.txt_info_peso)
        textInfoDiagnostico = findViewById(R.id.txt_diagnostico)
        buttonNovamente = findViewById(R.id.novo_calculo)

        val bundle = intent.extras

        if(bundle != null){
             val pesoPaciente = bundle?.getDouble("peso",0.0)?: 0.0
             val alturaPaciente = bundle?.getDouble("altura",0.0)?: 0.0

            textInfoPeso.text = "Peso informado: $pesoPaciente kg"
            textInfoAltura.text = "Altura informada: $alturaPaciente m"

            val imc = pesoPaciente / (alturaPaciente * alturaPaciente)

            val resultado = when {
                imc < 18.5 -> "Seu IMC indica Baixo peso"
                imc in 18.5..24.9 -> "Seu IMC indica Normal"
                imc in 25.0..29.9 -> "Seu IMC indica Sobrepeso"
                else -> "Seu IMC indica Obesidade"
            }

            textInfoDiagnostico.text = resultado
        }

        buttonNovamente.setOnClickListener {
            finish()
        }

    }


}