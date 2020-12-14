package me.kekhuay.miniblog.role

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    val name: RoleName
)
