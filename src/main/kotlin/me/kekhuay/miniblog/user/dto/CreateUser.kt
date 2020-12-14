package me.kekhuay.miniblog.user.dto

import me.kekhuay.miniblog.user.User

data class CreateUser(
    val username: String,
    val password: String
)

fun CreateUser.toUserEntity() = User(
    username = username,
    passwordHash = password,
)
