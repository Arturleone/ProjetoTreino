package com.example.arturmodulo3.API

data class LoginResponse (
    val id: String,
    val nome: String,
    val email: String,
    val tipoUsur: String,
    val dr: String,
    val token: String
)