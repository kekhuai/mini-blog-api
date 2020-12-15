package me.kekhuay.miniblog.blog.dto

import me.kekhuay.miniblog.blog.Blog
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class BlogRequest(
    @get:NotBlank
    @get:Size(min = 4, max = 128)
    val name: String?,
    @get:NotBlank
    @get:Size(max = 16)
    val status: String?,
    val content: String?,
    val category: String?
)

fun BlogRequest.toEntity(): Blog = Blog(
    name = name!!,
    status = status!!,
    content = content,
    category = category
)
