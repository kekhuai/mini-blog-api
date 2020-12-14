package me.kekhuay.miniblog.security

import com.fasterxml.jackson.annotation.JsonIgnore
import me.kekhuay.miniblog.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(
    private val id: Long,
    private val username: String,
    @JsonIgnore
    private val password: String,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {
    companion object {
        fun create(user: User): UserPrincipal {
            val authorities: List<GrantedAuthority> = user.roles.map { SimpleGrantedAuthority(it.name.name) }.toList()
            return UserPrincipal(
                id = user.id,
                username = user.username,
                password = user.password,
                authorities = authorities
            )
        }
    }

    fun getId(): Long {
        return id
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserPrincipal

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
