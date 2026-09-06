package com.example.ejercicio1app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class CentimetrosApies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_centimetros_apies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //compo
        val btnMenu = findViewById<Button>(R.id.btnMenu)
        val btnCalcPies = findViewById<Button>(R.id.btnCalcPies)
        val inputCentimetros = findViewById<EditText>(R.id.txtInputCm)
        val outPies = findViewById<TextView>(R.id.txvCmApies)

        //click calculo
        btnCalcPies.setOnClickListener {
            //val inputCm = inputCentimetros.Text.tostring().toInt()
            val inputCm = inputCentimetros.text.toString().toDoubleOrNull() ?: 0.0
            if (inputCm > 0) {
                val totalPies = inputCm / 30.48
                // Formatea el resultado a 2 decimales (ej: 3.28 ft)
                outPies.text = String.format("%.2f ft", totalPies)
            } else {
                Toast.makeText(this, "Debe ser mayor a cero", Toast.LENGTH_SHORT).show()
            }
        }
        //click neby
        btnMenu.setOnClickListener {
            finish()
        }
    }
}