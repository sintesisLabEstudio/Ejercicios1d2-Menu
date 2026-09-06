package com.example.ejercicio1app

import android.widget.Toast
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Imc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Componentes de la interfaz
        val btnMenu = findViewById<Button>(R.id.btnMenu)
        val btnCalculo = findViewById<Button>(R.id.btnCalcularIMC)
        val txvOutImc = findViewById<TextView>(R.id.txvImc)
        val txtInputEstatura = findViewById<EditText>(R.id.txtInputEstatura)
        val txtInputPeso = findViewById<EditText>(R.id.txtInputPeso)

        btnCalculo.setOnClickListener {
            // 1. Sanitización de texto (reemplazar comas por puntos)
            val strEstatura = txtInputEstatura.text.toString().trim().replace(",", ".")
            val strPeso = txtInputPeso.text.toString().trim().replace(",", ".")

            // 2. Conversión segura a Double
            val estatura = strEstatura.toDoubleOrNull() ?: 0.0
            val peso = strPeso.toDoubleOrNull() ?: 0.0

            // 3. Validaciones antes de calcular
            if (estatura <= 0.0 || estatura > 2.5) {
                Toast.makeText(this, "Ingrese una estatura válida (ej: 1.75)", Toast.LENGTH_SHORT).show()
                txtInputEstatura.requestFocus()
                return@setOnClickListener
            }

            if (peso <= 0.0) {
                Toast.makeText(this, "Ingrese un peso válido", Toast.LENGTH_SHORT).show()
                txtInputPeso.requestFocus()
                return@setOnClickListener
            }

            // 4. Cálculo del IMC usando Double
            val imc = peso / (estatura * estatura)

            // 5. Diagnóstico según la OMS
            val diagnostico = when {
                imc < 18.5 -> "Bajo peso"
                imc < 25.0 -> "Peso normal"
                imc < 30.0 -> "Sobrepeso"
                else -> "Obesidad"
            }

            // 6. Presentación del resultado con 2 decimales
            val mensaje = String.format("Tu IMC es: %.2f (%s)", imc, diagnostico)
            txvOutImc.text = mensaje
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

            // 7. Limpieza de campos post-cálculo
            txtInputEstatura.text.clear()
            txtInputPeso.text.clear()
            txtInputPeso.requestFocus()
        }

        btnMenu.setOnClickListener {
            finish()
        }
    }
}