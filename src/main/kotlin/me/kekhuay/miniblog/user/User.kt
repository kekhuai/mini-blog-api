package me.kekhuay.miniblog.user

import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "user", indexes = [Index(columnList = "username")])
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "username", unique = true, nullable = false, length = 64)
    val username: String,

    @Column(name = "password_hash", nullable = false, length = 128)
    val passwordHash: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime,

    @Version
    val version: Long
) : Persistable<Long>, Serializable {
    override fun getId(): Long {
        return id
    }

    override fun isNew(): Boolean {
        return 0L != id
    }

    @PrePersist
    fun prePersist() {
        createdAt = OffsetDateTime.now()
        updatedAt = OffsetDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = OffsetDateTime.now()
    }
}
