package com.example.ejercicio1app

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalleVentas : AppCompatActivity() {

    private val sueldoBase = 300000.0
    private val bonoPorVenta = 0.12

    // Lista para guardar las referencias de todos los EditText generados
    private val listaInputs = mutableListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_ventas)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //componentes
        val container = findViewById<LinearLayout>(R.id.containerVentas)
        val btnCalcular = findViewById<Button>(R.id.btnCalcularFinal)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            finish()
        }

        // Obtener la cantidad de ventas enviada desde SueldoNeto
        val cantVentas = intent.getIntExtra("CANTIDAD_VENTAS", 0)

        // Generar dinámicamente N casillas de texto
        for (i in 1..cantVentas) {
            val input = EditText(this).apply {
                hint = "Monto Venta $i ($)"
                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 16)
                }
            }
            // Guardar referencia en la lista
            listaInputs.add(input)
            // Insertar la casilla en el LinearLayout (equivalente a appendChild en JS)
            container.addView(input)
        }

        // Evento al presionar Calcular Sueldo Neto
        btnCalcular.setOnClickListener {
            var totalMontoVentas = 0.0
            for (input in listaInputs) {
                val monto = input.text.toString().toDoubleOrNull() ?: 0.0
                totalMontoVentas += monto
            }

            val totalBono = totalMontoVentas * bonoPorVenta
            val sueldoNeto = sueldoBase + totalBono

            //ALERTA DE DIALOGO PRESENTA
            AlertDialog.Builder(this)
                .setTitle("Resumen Sueldo Neto")
                .setMessage(
                    "Sueldo Base: $$sueldoBase\n" +
                            "Ventas Ingresadas ($cantVentas): $$totalMontoVentas\n" +
                            "Bono (12%): $$totalBono\n" +
                            "--------------------------------\n" +
                            "SUELDO NETO: $$sueldoNeto"
                )
                .setPositiveButton("Aceptar", null)
                .show()
        }
    }
}