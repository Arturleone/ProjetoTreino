package com.example.arturmodulo3.API

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    val url = "https://apieuvounatrip.azurewebsites.net/"

    val usuario: UsuarioAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(UsuarioAPI::class.java)
    }
}