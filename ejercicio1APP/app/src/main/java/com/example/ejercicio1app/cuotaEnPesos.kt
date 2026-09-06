package com.example.ejercicio1app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class CuotaEnPesos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuota_en_pesos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Componentes
        val btnCalculo = findViewById<Button>(R.id.btnCalculoCuota)
        val btnMenu = findViewById<Button>(R.id.btnMenu)
        val txtInputValUf = findViewById<EditText>(R.id.txtInputValorUf)
        val txtInputCosto = findViewById<EditText>(R.id.txtInputCostoCred)
        val txvOutCuota = findViewById<TextView>(R.id.txvCuota)

        // Botón cálculo
        btnCalculo.setOnClickListener {
            val strUf = txtInputValUf.text.toString().trim()
            val strCosto = txtInputCosto.text.toString().trim()

            // 1. Normalización Inteligente
            val valUf = normalizarMonto(strUf)
            val costoUf = normalizarMonto(strCosto)

            // Validación de seguridad
            if (valUf <= 0.0 || costoUf <= 0.0) {
                Toast.makeText(this, "Por favor, ingrese valores válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Cálculo
            val resultado = valUf * costoUf

            // 3. Formateo de Salida (Estilo Chile: $ 204.401.800)
            val symbols = DecimalFormatSymbols(Locale.GERMAN) // Usa puntos para miles
            val formatter = DecimalFormat("$ #,###", symbols)
            val cuotaFormateada = formatter.format(Math.round(resultado))

            // 4. Mostrar Resultados
            txvOutCuota.text = cuotaFormateada
            
            // Toast de verificación para el desarrollador
            Toast.makeText(this, "Calc: $valUf * $costoUf", Toast.LENGTH_SHORT).show()

            // Limpieza y enfoque
            txtInputCosto.text.clear()
            txtInputValUf.text.clear()
            txtInputValUf.requestFocus()
        }

        // Botón volver al menú
        btnMenu.setOnClickListener { finish() }
    }

    /**
     * Normaliza montos manejando puntos de miles y comas decimales.
     */
    private fun normalizarMonto(monto: String): Double {
        if (monto.isEmpty()) return 0.0
        return try {
            var temp = monto.trim()
            
            // Caso 1: Formato 40.880,36 (Chile completo)
            if (temp.contains(".") && temp.contains(",")) {
                temp = temp.replace(".", "").replace(",", ".")
            }
            // Caso 2: Formato 40880,36 (Solo decimal con coma)
            else if (temp.contains(",")) {
                temp = temp.replace(",", ".")
            }
            // Caso 3: Formato 40.880 (Podría ser miles o decimal)
            else if (temp.contains(".")) {
                val partes = temp.split(".")
                // Si el punto está seguido de exactamente 3 dígitos, asumimos que es separador de miles
                if (partes.size > 1 && partes.last().length == 3) {
                    temp = temp.replace(".", "")
                }
            }
            
            temp.toDoubleOrNull() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }
}
