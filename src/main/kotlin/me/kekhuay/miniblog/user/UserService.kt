package me.kekhuay.miniblog.user

import me.kekhuay.miniblog.user.dto.CreateUser

interface UserService {
    fun register(dto: CreateUser): User
}
