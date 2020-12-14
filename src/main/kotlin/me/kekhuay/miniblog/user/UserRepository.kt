package me.kekhuay.miniblog.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByIdIn(userIds: List<Long>): List<User>
    fun findByUsername(username: String): Optional<User>
    fun existsByUsername(username: String): Boolean
}
