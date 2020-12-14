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
    private val id: Long,

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private val username: String,

    @Column(name = "password", nullable = false, length = 128)
    private var password: String,

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    private val roles: Set<Role>,

    @Version
    private val version: Long
) : Persistable<Long>, Serializable, DateAudit() {
    constructor(username: String, passwordHash: String) :
            this(
                id = 0,
                username = username,
                password = passwordHash,
                roles = emptySet<Role>(),
                version = 0
            )

    override fun getId(): Long {
        return id
    }

    override fun isNew(): Boolean {
        return 0L == id
    }
}
