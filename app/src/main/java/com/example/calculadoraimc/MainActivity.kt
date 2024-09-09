package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalcular: Button

    private lateinit var textInputPeso: TextInputLayout
    private lateinit var editTextPeso: TextInputEditText

    private lateinit var textInputAltura: TextInputLayout
    private lateinit var editTextAltura: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textInputPeso = findViewById(R.id.txt_input_peso)
        textInputAltura = findViewById(R.id.txt_input_altura)

        editTextAltura = findViewById(R.id.edit_txt_altura)
        editTextPeso = findViewById(R.id.edit_txt_peso)
        btnCalcular = findViewById(R.id.btn_calcular)

        btnCalcular.setOnClickListener {
            preparacaoCalculoImc()
        }

    }

    private fun validarCampos(pacientePeso: String, pacienteAltura: String): Boolean{
        textInputAltura.error = null
        textInputPeso.error = null

        if (pacientePeso.isEmpty()) {
            textInputPeso.error = "Digite o seu peso"
            return false
        } else if (pacienteAltura.isEmpty()) {
            textInputAltura.error = "Digite a sua altura"
            return false
        }
        return true
    }
    private fun preparacaoCalculoImc() {

        val altura = editTextAltura.text.toString()
        val peso = editTextPeso.text.toString()

        val resultadoValidacao = validarCampos(peso, altura)

        if(resultadoValidacao){
            val intent = Intent(this, ResultsActivity::class.java)

            if (peso.isNotEmpty() && altura.isNotEmpty()) {
                intent.putExtra("peso", peso.toDouble())
                intent.putExtra("altura", altura.toDouble())
            }

            startActivity(intent)
        }
    }
}