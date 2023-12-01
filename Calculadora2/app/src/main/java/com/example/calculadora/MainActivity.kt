package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var op1 = 0
    private var op2 = 0
    private var operador = ""
    private var textView = ""
    private var resultado = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recuperar datos guardados
        op1 = savedInstanceState?.getInt("textView") ?: 0
        binding.textview.text = textView

        //escuchador de click
        binding.numero0.setOnClickListener(this)
        binding.numero1.setOnClickListener(this)
        binding.numero2.setOnClickListener(this)
        binding.numero3.setOnClickListener(this)
        binding.numero4.setOnClickListener(this)
        binding.numero5.setOnClickListener(this)
        binding.numero6.setOnClickListener(this)
        binding.numero7.setOnClickListener(this)
        binding.numero8.setOnClickListener(this)
        binding.numero9.setOnClickListener(this)
        binding.operadorBorrar.setOnClickListener(this)
        binding.operadorIgual.setOnClickListener(this)
        binding.operadorSuma.setOnClickListener(this)
        binding.operadorResta.setOnClickListener(this)
        binding.operadorMultiplicacion.setOnClickListener(this)
        binding.operadorDivision.setOnClickListener(this)
        binding.operadorResta.setOnClickListener(this)
        binding.operadorSeno?.setOnClickListener(this)
        binding.operadorCoseno?.setOnClickListener(this)
        binding.operadorTangente?.setOnClickListener(this)
        binding.operadorRaiz?.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("textView", textView.toInt())
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            binding.numero0.id,
            binding.numero1.id,
            binding.numero2.id,
            binding.numero3.id,
            binding.numero4.id,
            binding.numero5.id,
            binding.numero6.id,
            binding.numero7.id,
            binding.numero8.id,
            binding.numero9.id -> {
                var numeroGuardado = (v as Button).text.toString().toInt()
                if (operador.isEmpty()) {
                    op1 = op1 * 10 + numeroGuardado
                    textView = op1.toString()
                }else if (!operador.isEmpty()){
                    op2 = op2 * 10 + numeroGuardado
                    textView = op2.toString()
                }
                actualizarPantalla()
            }

            binding.operadorSuma.id,
            binding.operadorResta.id,
            binding.operadorMultiplicacion.id,
            binding.operadorDivision.id -> {
                operador = (v as Button).text.toString()
                textView = operador
                actualizarPantalla()
            }
            binding.operadorBorrar.id -> {
                op1 = 0
                textView = op1.toString()
                op2 = 0
                textView = op2.toString()
                operador = ""
                textView = operador
                actualizarPantalla()
            }
            binding.operadorIgual.id ->{
                realizarOperacion(operador)
                op1 = resultado
                op2 = 0
                textView = op1.toString()
                actualizarPantalla()
            }
        }

    }

    private fun actualizarPantalla() {
        binding.textview.text = textView
    }
    private fun realizarOperacion(operador :String){
        when(operador){
            "+"-> {
                resultado = sumar()
            }
            "-"-> {
                resultado = restar()
            }
            "X"-> {
                resultado = multiplicar()
            }
            "/"-> {
                resultado = dividir()
            }
        }
    }
    private fun sumar():Int{
        return op1 + op2
    }
    private fun restar():Int{
        return op1 - op2
    }
    private fun multiplicar():Int{
        return op1 * op2
    }
    private fun dividir():Int{
        return op1 / op2
    }

}
