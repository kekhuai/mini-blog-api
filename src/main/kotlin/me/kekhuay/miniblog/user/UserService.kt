package me.kekhuay.miniblog.user

interface UserService {
    fun isUsernameAlreadyExists(username: String): Boolean
}
