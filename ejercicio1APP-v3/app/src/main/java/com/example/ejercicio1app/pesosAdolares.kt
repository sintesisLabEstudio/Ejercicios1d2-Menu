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

class PesosAdolares : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pesos_adolares)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //boton calcuilo click
        val btnCalculo= findViewById<Button>(R.id.btnCalc)
        btnCalculo.setOnClickListener {
            //COMPS
            //input
            val txtValorDolar= findViewById<EditText>(R.id.txtValorDolar)
            val txtPesos= findViewById<EditText>(R.id.txtPesos)

            //output
            val txvValorDolar= findViewById<TextView>(R.id.txvValDolar)
            val txvPesos = findViewById<TextView>(R.id.txvPesos)
            val txvDolares = findViewById<TextView>(R.id.txvDolares)

            //valores
            val valorDolar=txtValorDolar.text.toString().toDoubleOrNull() ?: 0.0
            var pesos = txtPesos.text.toString().toDoubleOrNull() ?: 0.0

            //si monto en pesos es cero
            if(pesos==0.0){
                //si monto en pesos es cero
                Toast.makeText(this,"Ingrese pesos", Toast.LENGTH_LONG).show()
                //enfoca
                txtPesos.requestFocus()
            } else {
                //calculo validando div por cero
                var totalDolares=0.0
                if (valorDolar > 0.0) {
                    totalDolares= pesos/valorDolar
                } else {
                    Toast.makeText(this,"el valor del dolar no puede ser 0", Toast.LENGTH_LONG).show()
                    txtValorDolar.requestFocus()
                }

                //solo si el valor dolar es distinto de cero y hay pesos hay calculo
                if (totalDolares>0.0 && pesos >0.0){
                    //presenta
                    txvValorDolar.text=valorDolar.toString()
                    txvPesos.text=pesos.toString()
                    //txvDolares.text= totalDolares.toString()
                    txvDolares.text= String.format("%.2f", totalDolares) //formato 2  decimas
                    //mensaje
                    val mensaje= "Con el valor del dolar a ${valorDolar.toString()} y con ${pesos.toString()} Pesos Tiene ${totalDolares.toString()} dolares"
                    Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
                }
            }
        }

        //boton volver al menu
        val btnMenu = findViewById<Button>(R.id.btnMenu)
        btnMenu.setOnClickListener {
            finish()
        }
    }
}