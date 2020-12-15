package me.kekhuay.miniblog.blog

import me.kekhuay.miniblog.blog.dto.BlogRequest
import me.kekhuay.miniblog.dto.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
@RequestMapping(path = ["/api/v1/blogs"])
class BlogController(private val blogService: BlogService) {
    @PostMapping
    @PreAuthorize(value = "hasRole('USER')")
    fun create(@Valid @RequestBody blogRequest: BlogRequest): ResponseEntity<*> {
        val blog = blogService.create(blogRequest)

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(blog.id)
            .toUri()

        return ResponseEntity.created(location)
            .body(ApiResponse(true, "Blog created successfully"))
    }

    @DeleteMapping(path = ["/{id}"])
    @PreAuthorize(value = "@blogServiceImpl.isOwner(authentication, #id)")
    fun delete(@Valid @NotNull @PathVariable id: Long): ResponseEntity<*> {
        blogService.delete(id)

        return ResponseEntity.ok()
            .body(ApiResponse(true, "Blog deleted successfully"))
    }
}
