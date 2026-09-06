package com.example.ejercicio1app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //COMPONENTE
        val btnIr = findViewById<Button>(R.id.btnIr)
        //BOTON CLICK

        btnIr.setOnClickListener {
            //COMPONENTE
            val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text.toString()
            if (inputOpc.isNotEmpty()) {
                menu(inputOpc)
            } else {
                Toast.makeText(this, "Por favor ingrese una opción", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun menu(opcion: String) {
        val numOpc = opcion.toIntOrNull()
        if (numOpc != null && numOpc in 0..9) {
            when (numOpc) {
                0 -> finishAffinity() // finish() Salir
                1 -> {
                    //limpia txt opcion
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    //carga
                    val intent = Intent(this, Suma2Numeros::class.java)
                    startActivity(intent)
                }
                2 -> {
                    //limpia txt opcion
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    val intent  = Intent(this, PesosAdolares::class.java)
                    startActivity(intent)
                }
                3 -> {
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    val intent  = Intent(this,CuotaEnPesos::class.java)
                    startActivity(intent)
                }
                4 -> {
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    val intent  = Intent(this,Imc::class.java)
                    startActivity(intent)
                }
                5 -> {
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    val intent  = Intent(this, SueldoAnual::class.java)
                    startActivity(intent)
                }
                6-> {
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    val intent  = Intent(this, CentimetrosApies::class.java)
                    startActivity(intent)
                }
                7-> {
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    val intent  = Intent(this, Ciclo10::class.java)
                    startActivity(intent)
                }
                8-> {
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    val intent  = Intent(this, FaltasPermitadas::class.java)
                    startActivity(intent)
                }
                9-> {
                    val inputOpc = findViewById<EditText>(R.id.txtNumOpcion).text
                    inputOpc.clear()
                    val intent  = Intent(this, SueldoNeto::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Opción $numOpc seleccionada (en desarrollo)", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            val mensaje = "Debe ingresar un numero entre 0 y 8"
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }
}
