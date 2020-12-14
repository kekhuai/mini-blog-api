package me.kekhuay.miniblog.role

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private val name: RoleName
)
