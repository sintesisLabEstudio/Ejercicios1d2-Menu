package com.example.ejercicio1app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SueldoNeto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sueldo_neto)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //constantes
        val bonoPorventa = 0.12
        val sueldoBase = 300000

        // Referencia a componentes
        val btnMenu = findViewById<Button>(R.id.btnMenu)
        val btnCalculo = findViewById<Button>(R.id.btnCalcNeto) // O id/btnCalculo segun tu XML
        val txtCantVentas = findViewById<EditText>(R.id.txtCantVentas)

        btnMenu.setOnClickListener {
            finish()
        }

        //calculla llamando a ventana enviando datos
        btnCalculo.setOnClickListener {
            val cantVentas = txtCantVentas.text.toString().toIntOrNull()

            if (cantVentas != null && cantVentas > 0) {
                // Crear el Intent hacia la pantalla DetalleVentas
                val intent = Intent(this, DetalleVentas::class.java)
                intent.putExtra("CANTIDAD_VENTAS", cantVentas)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Ingrese una cantidad válida mayor a 0", Toast.LENGTH_SHORT).show()
            }
        }

        //calcula solo 3 ventas
        val btnCalc3= findViewById<Button>(R.id.btn3Ventas)
        btnCalc3.setOnClickListener {
            //valores en texto
            val venta1= findViewById<EditText>(R.id.txtInpVenta1).text.toString().toIntOrNull() ?:0
            val venta2= findViewById<EditText>(R.id.txtInpVenta2).text.toString().toIntOrNull() ?:0
            val venta3= findViewById<EditText>(R.id.txtInpVenta3).text.toString().toIntOrNull() ?:0

            //calculo
            //suma valors
            val totaVentas= venta1 + venta2 + venta3
            val netoTotal= sueldoBase + ( totaVentas * bonoPorventa )

            //presenta
            val txtNeto3Ventas = findViewById<TextView>(R.id.txvRes3ventas)
            txtNeto3Ventas.text="Sueldo Neto: $${netoTotal.toInt()}"
        }
    }
}