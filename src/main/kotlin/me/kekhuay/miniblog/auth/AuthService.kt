package me.kekhuay.miniblog.auth

import me.kekhuay.miniblog.auth.dto.SignInRequest
import me.kekhuay.miniblog.auth.dto.SignUpRequest
import me.kekhuay.miniblog.user.User

interface AuthService {
    fun signUp(signUpRequest: SignUpRequest): User
    fun signIn(signInRequest: SignInRequest): String
}
