package me.kekhuay.miniblog.user

import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun isUsernameAlreadyExists(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }
}
