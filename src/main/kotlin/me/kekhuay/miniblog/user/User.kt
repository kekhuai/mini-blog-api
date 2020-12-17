package me.kekhuay.miniblog.user

import com.fasterxml.jackson.annotation.JsonIgnore
import me.kekhuay.miniblog.audit.DateAudit
import me.kekhuay.miniblog.role.Role
import org.springframework.data.domain.Persistable
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Table(name = "users", indexes = [Index(columnList = "username")])
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "username", unique = true, nullable = false, length = 64)
    val username: String,

    @get:JsonIgnore
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
    @get:JsonIgnore
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

    @JsonIgnore
    override fun isNew(): Boolean {
        return 0L == id
    }
}
