package com.example.arturmodulo3

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.arturmodulo3.API.RetrofitClient
import com.example.arturmodulo3.API.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        val voltar = findViewById<ImageView>(R.id.imageView)
        val nome = findViewById<EditText>(R.id.editTextText)
        val email = findViewById<EditText>(R.id.editTextText2)
        val senha = findViewById<EditText>(R.id.editTextText3)
        val salvar = findViewById<Button>(R.id.button4)
        val cancelar = findViewById<Button>(R.id.button3)

        voltar.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        salvar.setOnClickListener{
            if (nome.text.toString().isNotEmpty() && email.text.toString().isNotEmpty() && senha.text.toString().isNotEmpty()){
                val usuario2 = Usuario(nome.text.toString(), email.text.toString(), senha.text.toString(), "MG","1")
                cadastrarUsuario(usuario2)

            }
        }

        cancelar.setOnClickListener{
            AlertDialog.Builder(this).setPositiveButton("Sim") {_,_ ->
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
                .setNegativeButton("Não") {_,_ ->

            }
                .show()
        }


    }

    private fun cadastrarUsuario(usuario: Usuario) {
        RetrofitClient.usuario.realizarCadastro(usuario).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                startActivity(Intent(this@CadastroActivity, LoginActivity::class.java))
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
            }
        })
    }
}