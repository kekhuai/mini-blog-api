package me.kekhuay.miniblog.user

import me.kekhuay.miniblog.user.dto.CreateUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val repository: UserRepository
) : UserService {
    @Transactional
    override fun register(dto: CreateUser): User {
        val user = User(dto.username, dto.password)
        return repository.save(user)
    }
}
