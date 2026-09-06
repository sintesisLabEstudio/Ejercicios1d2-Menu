package com.example.ejercicio1app

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SueldoAnual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sueldo_anual)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //componentes
        val btnMenu= findViewById<Button>(R.id.btnMenu)
        val txtInputMensual= findViewById<EditText>(R.id.txtInSueldoMes)
        val txvOutAnual=findViewById<TextView>(R.id.txvOutSueldoAnual)
        val btnCalc = findViewById<Button>(R.id.btnCalculoAnual)

        //click
        btnCalc.setOnClickListener {
            val sueldoMes = txtInputMensual.text.toString().toInt()
            if(sueldoMes>0){
                val total = sueldoMes * 12
                txvOutAnual.text=total.toString()
            }
        }

        //salir
        btnMenu.setOnClickListener {
            finish()
        }

    }
}