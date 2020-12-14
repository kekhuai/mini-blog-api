package me.kekhuay.miniblog.user

import me.kekhuay.miniblog.exception.AppException
import me.kekhuay.miniblog.role.Role
import me.kekhuay.miniblog.role.RoleName
import me.kekhuay.miniblog.role.RoleRepository
import me.kekhuay.miniblog.auth.dto.SignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val encoder: PasswordEncoder
) : UserService {
    override fun isUsernameAlreadyExists(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override fun register(request: SignUpRequest): User {
        val user = User(request.username, encoder.encode(request.password))
        val roles = HashSet<Role>(user.roles)
        val userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow { AppException("User Role not set.") }
        roles.add(userRole)
        user.roles = roles
        return userRepository.save(user)
    }
}
