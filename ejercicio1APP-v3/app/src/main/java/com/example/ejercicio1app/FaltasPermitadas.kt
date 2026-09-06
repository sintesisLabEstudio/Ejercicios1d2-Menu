package com.example.ejercicio1app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.ceil

class FaltasPermitadas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_faltas_permitadas)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // CONSTANTES
        val clases = 12       // Total de clases
        val asisteMin = 0.70  // 70% asistencia mínima

        // COMPONENTES
        val btnMenu = findViewById<Button>(R.id.btnMenu)
        val txvMensaje = findViewById<TextView>(R.id.txvMensaje)
        val btnCalFaltas = findViewById<Button>(R.id.btnCalcFaltas)
        val txvFaltasP = findViewById<TextView>(R.id.txvFaltasP)

        // Mensaje multilínea usando comillas triples
        val mensaje = """
            Existe un alumno que utiliza periódicamente la ley del 
            menor esfuerzo, por lo cual necesita saber 
            la cantidad de veces que se le permite faltar 
            a la asignatura de Taller de Nuevas Tecnologías. 
            El módulo se compone de 12 clases 
            y la asistencia mínima es del 70%.
        """.trimIndent()

        txvMensaje.text = mensaje

        // Botón salir / menú
        btnMenu.setOnClickListener {
            finish()
        }

        // Click cálculo faltas
        btnCalFaltas.setOnClickListener {
            // Mínimo de clases a las que debe asistir obligatoriamente (12 * 0.70 = 8.4 -> 9 clases)
            val clasesMinimasAsistir = ceil(clases * asisteMin).toInt()

            // Máximo de faltas posibles sin perder la asignatura (12 - 9 = 3 faltas)
            val faltasPermitidas = clases - clasesMinimasAsistir

            // Cálculo del porcentaje exacto asistiendo a las clases requeridas
            val porcentajeConFaltas = ((clases - faltasPermitidas).toDouble() / clases) * 100

            txvFaltasP.text = "Puede faltar máximo $faltasPermitidas clases.\n(Asistencia resultante: ${porcentajeConFaltas.toInt()}%)"
        }
    }
}