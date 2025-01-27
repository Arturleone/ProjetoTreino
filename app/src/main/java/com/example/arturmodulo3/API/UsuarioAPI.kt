package com.example.arturmodulo3.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuarioAPI {
    @POST ("api/Usuarios")
    fun realizarCadastro(@Body usuario: Usuario): Call<Void>

    @POST ("api/Usuarios/Login")
    fun realizarLogin(
        @Query ("email") email: String,
        @Query ("senha") senha: String
    ): Call<LoginResponse>

    @GET ("api/Usuarios")
    fun listarUsuarios(): Call<List<UserDetails>>
}