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

class Suma2Numeros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suma2_numeros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //componente btns
        val btnSuma= findViewById<Button>(R.id.btnSuma)
        val btnMenu = findViewById<Button>(R.id.btnMenu)

        //boton click
        btnSuma.setOnClickListener {
            //componentes
            val txtNum1 = findViewById<EditText>(R.id.txtInputN1)
            val txtNum2 = findViewById<EditText>(R.id.txtInputN2)
            //valores
            val num1 = txtNum1.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = txtNum2.text.toString().toDoubleOrNull() ?: 0.0
            //calculo
            val tot= num1+num2
            val resultado= tot.toString()
            val respuesta="La suma de ${num1.toString()} + ${num2.toString()} = "+ resultado
            //presenta
            Toast.makeText(this,respuesta, Toast.LENGTH_LONG).show()
            //componente
            val txvNum1 = findViewById<TextView>(R.id.txvNum1)
            val txvNum2 = findViewById<TextView>(R.id.txvNum2)
            val txvTotal= findViewById<TextView>(R.id.txvResultado)
            //asignando valores
            txvNum1.text= num1.toString()
            txvNum2.text= num2.toString()
            txvTotal.text=resultado
            //limpia
            txtNum1.text.clear()
            txtNum2.text.clear()
            //enfoca
            txtNum1.requestFocus()
       }

        //boton menu
        btnMenu.setOnClickListener {
            finish()
        }

    }
}