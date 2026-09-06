package com.example.ejercicio1app
import android.widget.Button
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.collections.plusAssign

class Ciclo10 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ciclo10)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //compo btn
        val btnMenu=findViewById<Button>(R.id.btnMenu)
        val btnWhile10= findViewById<Button>(R.id.btnWhile10)
        val btnDoWhile10= findViewById<Button>(R.id.btnDoWhile10)
        val btnFor10= findViewById<Button>(R.id.btnFor10)
        val txvWhile= findViewById<TextView>(R.id.txvResultadoWile)
        val txvDoWhile= findViewById<TextView>(R.id.txvResultadoDoWhile)
        val txvFor10= findViewById<TextView>(R.id.txvResultadoFor10)

        //btn menu
        btnMenu.setOnClickListener {
            finish()
        }

        //btn while
        btnWhile10.setOnClickListener {
            val resultado=cicloWhile10()
            txvWhile.text=resultado.toString()
        }

        //btn do while
        btnDoWhile10.setOnClickListener {
            val resultado=cicloDoWhile10()
            txvDoWhile.text=resultado.toString()
        }

        //btn for
        btnFor10.setOnClickListener{
            val resultado=cicloFor10()
            txvFor10.text=resultado.toString()
        }
    }

    fun cicloWhile10():Int{
        var contador = 0
        var repeticiones=0
        while(contador<=10){
            repeticiones++
            contador++
        }
        return repeticiones
    }
    fun cicloDoWhile10():Int{
        var contador=0
        var repeticiones=0
        do {
            repeticiones++
            contador++
       } while(contador<=10)
       return repeticiones
    }
    fun cicloFor10():Int{
        var suma=0
        var repeticiones=0
        for (i in 0..10) {
            repeticiones++
        }
        return repeticiones
    }
}