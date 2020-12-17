package me.kekhuay.miniblog.blog

import me.kekhuay.miniblog.blog.dto.BlogRequest
import me.kekhuay.miniblog.blog.dto.toEntity
import me.kekhuay.miniblog.exception.ResourceNotFoundException
import me.kekhuay.miniblog.security.UserPrincipal
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BlogServiceImpl(private val blogRepository: BlogRepository) : BlogService {
    @Transactional
    override fun create(blogRequest: BlogRequest): Blog {
        return blogRepository.save(blogRequest.toEntity())
    }

    override fun delete(id: Long) {
        blogRepository.deleteById(id)
    }

    override fun isOwner(authentication: Authentication, id: Long): Boolean {
        val blog = blogRepository.findById(id).orElseThrow { ResourceNotFoundException("Blog", "id", id) }
        val userPrincipal = authentication.principal as UserPrincipal
        return blog.createdBy == userPrincipal.getId()
    }

    override fun read(): List<Blog> {
        return blogRepository.findAll()
    }

    override fun readById(id: Long): Blog {
        return blogRepository.findById(id).orElseThrow { ResourceNotFoundException("Blog", "id", id) }
    }

    @Transactional
    override fun update(id: Long, blogRequest: BlogRequest): Blog {
        val blog = blogRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("Blog", "id", id) }
        blog.name = blogRequest.name
        blog.status = blogRequest.status
        blog.category = blogRequest.category
        blog.content = blogRequest.content
        return blogRepository.save(blog)
    }
}
