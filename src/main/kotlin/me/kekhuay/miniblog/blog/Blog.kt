package me.kekhuay.miniblog.blog

import me.kekhuay.miniblog.audit.UserDateAudit
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "blogs")
data class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "name", length = 128, nullable = false)
    val name: String,

    @Column(name = "status", length = 16, nullable = false)
    val status: String,

    @Column(name = "content", length = 1024)
    val content: String?,

    @Column(name = "category", length = 128)
    val category: String?,
) : UserDateAudit() {
    constructor(name: String, status: String, content: String?, category: String?) :
            this(
                id = 0,
                name = name,
                status = status,
                content = content,
                category = category
            )
}
