package com.soulmate.models

import javax.persistence.*

@Entity
@Table(name = "member_role")
class MemberRole {
    companion object {
        const val ROLE_ADMIN = "ADMIN"
        const val ROLE_AUTHORIZED = "AUTHORIZED"
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "role")
    var role: String = ""

    @ManyToMany(mappedBy = "roles")
    val members: Set<Member> = setOf()
}