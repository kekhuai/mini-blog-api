package me.kekhuay.miniblog.user

import me.kekhuay.miniblog.user.dto.CreateUser
import me.kekhuay.miniblog.user.dto.toUserEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/v1/user"])
class UserController(private val service: UserService) {
    @PostMapping
    fun register(@RequestBody dto: CreateUser) {
        service.register(dto)
    }
}
