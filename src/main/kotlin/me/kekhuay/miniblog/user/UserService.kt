package me.kekhuay.miniblog.user

import me.kekhuay.miniblog.auth.dto.SignUpRequest

interface UserService {
    fun isUsernameAlreadyExists(username: String): Boolean
    fun register(request: SignUpRequest): User
}
