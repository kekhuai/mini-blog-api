package me.kekhuay.miniblog.auth

import me.kekhuay.miniblog.user.UserService
import me.kekhuay.miniblog.dto.ApiResponse
import me.kekhuay.miniblog.auth.dto.SignUpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/api/v1/auth"])
class AuthController(private val service: UserService) {
    @PostMapping(path = ["/signup"])
    fun register(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<*> {
        if (service.isUsernameAlreadyExists(signUpRequest.username)) {
            return ResponseEntity(
                ApiResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST
            )
        }
        val result = service.register(signUpRequest)
        val location = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/api/v1/users/{username}")
            .buildAndExpand(result.username)
            .toUri()
        return ResponseEntity.created(location)
            .body(ApiResponse(true, "User registered successfully"))
    }
}
