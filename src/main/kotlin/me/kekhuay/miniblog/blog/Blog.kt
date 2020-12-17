package me.kekhuay.miniblog.blog

import me.kekhuay.miniblog.audit.UserDateAudit
import me.kekhuay.miniblog.user.User
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "blogs")
data class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "name", length = 128, nullable = false)
    var name: String?,

    @Column(name = "status", length = 16, nullable = false)
    var status: String?,

    @Column(name = "content", length = 1024)
    var content: String?,

    @Column(name = "category", length = 128)
    var category: String?,

    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "id", insertable = false, updatable = false)
    val author: User?
) : UserDateAudit() {
    constructor(name: String, status: String, content: String?, category: String?) :
            this(
                id = 0,
                name = name,
                status = status,
                content = content,
                category = category,
                author = null
            )
}
