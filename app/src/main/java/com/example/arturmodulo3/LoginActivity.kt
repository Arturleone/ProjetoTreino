package com.example.arturmodulo3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.arturmodulo3.API.LoginResponse
import com.example.arturmodulo3.API.RetrofitClient
import com.example.arturmodulo3.API.UserDetails
import okhttp3.Callback
import okhttp3.internal.http2.Hpack
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val logar = findViewById<Button>(R.id.button2)
        val cadastrar = findViewById<Button>(R.id.button)

        cadastrar.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
            finish()
        }

        logar.setOnClickListener{
            val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
            val senha = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            realizarLogin(email, senha)


        }


    }

    fun realizarLogin(email: String, senha: String) {
        RetrofitClient.usuario.realizarLogin(email, senha)
            .enqueue(object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        listarUsuarios(email)
                    } else {
                        Toast.makeText(this@LoginActivity, "Campos Invalidos", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun listarUsuarios(email: String) {
        RetrofitClient.usuario.listarUsuarios()
            .enqueue(object : retrofit2.Callback<List<UserDetails>> {
                override fun onResponse(
                    call: Call<List<UserDetails>>,
                    response: Response<List<UserDetails>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val usuario = response.body()
                        val emailEncontrado = usuario?.find { it.email == email }

                        if (emailEncontrado != null) {
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Campos Invalidos!", Toast.LENGTH_SHORT).show()
                        }

                    }

                }

                override fun onFailure(call: Call<List<UserDetails>>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })
    }
}