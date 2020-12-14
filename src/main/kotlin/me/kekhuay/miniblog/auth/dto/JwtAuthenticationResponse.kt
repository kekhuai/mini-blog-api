package me.kekhuay.miniblog.auth.dto

data class JwtAuthenticationResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)
