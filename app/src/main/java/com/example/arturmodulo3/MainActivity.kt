package com.example.arturmodulo3

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Display.Mode
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val Exibicao = 3000
    private val ExibicaoFirst = 10000

    private lateinit var Textview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Textview = findViewById(R.id.textView)
        animacoes(Textview)

        val duracao = if (firstOpen()) ExibicaoFirst else Exibicao

        Handler().postDelayed({

            startActivity(Intent(this, LoginActivity::class.java))
            finish()


        }, duracao.toLong())

    }


    fun animacoes(Textview: TextView) {
        fadeIN(Textview)
        iniciarAnimacaoDeGradienteNoTexto(Textview)

    }

    private fun iniciarAnimacaoDeGradienteNoTexto(textView: TextView) {
        val conjuntosDeCores = listOf(
            intArrayOf(R.color.block, R.color.block2, R.color.block3),
            intArrayOf(R.color.block3, R.color.block, R.color.block2),
            intArrayOf(R.color.block2, R.color.block3, R.color.block)
        )

        var indiceConjuntoDeCorAtual = 0
        val handler = Handler(Looper.getMainLooper())
        val atualizadorDeGradiente = object : Runnable {
            override fun run() {
                val largura = textView.width.toFloat()
                val shader = LinearGradient(0f, 0f, largura, 0f,
                    conjuntosDeCores[indiceConjuntoDeCorAtual].map {
                        ContextCompat.getColor(this@MainActivity, it)
                    }.toIntArray(),
                    null, Shader.TileMode.CLAMP)
                textView.paint.shader = shader
                textView.invalidate()

                indiceConjuntoDeCorAtual = (indiceConjuntoDeCorAtual + 1) % conjuntosDeCores.size
                handler.postDelayed(this, 500)
            }
        }
        handler.post(atualizadorDeGradiente)
    }

    fun fadeIN(Textview: TextView) {
        val fadeIn = AlphaAnimation(0.0f, 1.0f).apply {
            duration = 2000
        }

        Textview.startAnimation(fadeIn)

    }


    fun firstOpen(): Boolean {
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val firstcommit = sharedPreferences.getBoolean("first", true)
        if (firstcommit) {
            sharedPreferences.edit().putBoolean("first", false).apply()
        }
        return firstcommit
    }
}