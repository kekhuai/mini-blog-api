package me.kekhuay.miniblog.user

import me.kekhuay.miniblog.audit.DateAudit
import me.kekhuay.miniblog.role.Role
import org.springframework.data.domain.Persistable
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "users", indexes = [Index(columnList = "username")])
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "username", unique = true, nullable = false, length = 64)
    val username: String,

    @Column(name = "password", nullable = false, length = 128)
    var password: String,

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: Set<Role>,

    @Version
    val version: Long
) : Persistable<Long>, Serializable, DateAudit() {
    constructor(username: String, passwordHash: String) :
            this(
                id = 0,
                username = username,
                password = passwordHash,
                roles = emptySet(),
                version = 0
            )

    override fun getId(): Long {
        return id
    }

    override fun isNew(): Boolean {
        return 0L == id
    }
}
