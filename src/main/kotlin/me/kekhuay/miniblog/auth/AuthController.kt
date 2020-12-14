package me.kekhuay.miniblog.auth

import me.kekhuay.miniblog.auth.dto.JwtAuthenticationResponse
import me.kekhuay.miniblog.auth.dto.SignInRequest
import me.kekhuay.miniblog.auth.dto.SignUpRequest
import me.kekhuay.miniblog.dto.ApiResponse
import me.kekhuay.miniblog.exception.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/api/v1/auth"])
class AuthController(
    private val authService: AuthService
) {
    @PostMapping(path = ["/sign-up"])
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<*> {
        try {
            val result = authService.signUp(signUpRequest)
            val location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/users/{username}")
                .buildAndExpand(result.username)
                .toUri()
            return ResponseEntity.created(location)
                .body(ApiResponse(true, "User registered successfully"))
        } catch (ex: BadRequestException) {
            val message = ex.message ?: ""
            return ResponseEntity(
                ApiResponse(false, message),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    @PostMapping(path = ["/sign-in"])
    fun signIn(@Valid @RequestBody signInRequest: SignInRequest): ResponseEntity<*> {
        val jwt = authService.signIn(signInRequest)
        return ResponseEntity.ok(JwtAuthenticationResponse(jwt))
    }
}
