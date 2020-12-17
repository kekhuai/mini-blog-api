package me.kekhuay.miniblog.blog

import me.kekhuay.miniblog.blog.dto.BlogRequest
import org.springframework.security.core.Authentication

interface BlogService {
    fun create(blogRequest: BlogRequest): Blog
    fun delete(id: Long)
    fun isOwner(authentication: Authentication, id: Long): Boolean
    fun read(): List<Blog>
    fun readById(id: Long): Blog
    fun update(id: Long, blogRequest: BlogRequest): Blog
}
