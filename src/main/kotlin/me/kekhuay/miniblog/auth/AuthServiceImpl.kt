package me.kekhuay.miniblog.auth

import me.kekhuay.miniblog.auth.dto.SignUpRequest
import me.kekhuay.miniblog.exception.AppException
import me.kekhuay.miniblog.exception.BadRequestException
import me.kekhuay.miniblog.role.Role
import me.kekhuay.miniblog.role.RoleName
import me.kekhuay.miniblog.role.RoleRepository
import me.kekhuay.miniblog.user.User
import me.kekhuay.miniblog.user.UserRepository
import me.kekhuay.miniblog.user.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val encoder: PasswordEncoder,
    private val userService: UserService
) : AuthService {
    override fun signUp(signUpRequest: SignUpRequest): User {
        if (userService.isUsernameAlreadyExists(signUpRequest.username)) {
            throw BadRequestException("Username is already taken!")
        }
        val user = User(signUpRequest.username, encoder.encode(signUpRequest.password))
        val roles = HashSet<Role>(user.roles)
        val userRole = roleRepository.findByName(RoleName.ROLE_USER)
            .orElseThrow { AppException("User Role not set.") }
        roles.add(userRole)
        user.roles = roles
        return userRepository.save(user)
    }
}
