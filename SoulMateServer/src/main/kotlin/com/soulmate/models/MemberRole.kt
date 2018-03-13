package com.soulmate.models

import javax.persistence.*

@Entity
@Table(name = "member_role")
class MemberRole {
    companion object {
        val ROLE_ADMIN = "ADMIN"
        val ROLE_AUTHORIZED = "AUTHORIZED"
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0
        get
        set

    @Column(name = "role")
    var role: String = ""
        get
        set

    @ManyToMany(mappedBy = "roles")
    val members: Set<Member> = setOf()
        get
}