package me.kekhuay.miniblog.blog

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BlogRepository : JpaRepository<Blog, Long> {
    override fun findById(id: Long): Optional<Blog>
    fun findByCreatedBy(userId: Long, pageable: Pageable): Page<Blog>
    fun countByCreatedBy(userId: Long): Long
    fun findByIdIn(ids: List<Long>): List<Blog>
    fun findByIdIn(ids: List<Long>, sort: Sort): List<Blog>
}
