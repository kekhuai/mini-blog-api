package me.kekhuay.miniblog.auth.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignUpRequest(
    @NotBlank
    @Size(min = 4, max = 64)
    val username: String,

    @NotBlank
    @Size(min = 8, max = 128)
    val password: String
)
