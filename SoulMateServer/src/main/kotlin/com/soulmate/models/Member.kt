package com.soulmate.models

import javax.persistence.*

@Entity
@Table(name = "member")
class Member {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0
        get
        set

    @Column(name = "email")
    var email: String = ""
        get
        set

    @Column(name = "password_hash")
    var passwordHash: String = ""
        get
        set

    @ManyToMany()
    @JoinTable(name = "member_roles",
            joinColumns = [(JoinColumn(name = "member_id"))],
            inverseJoinColumns = [(JoinColumn(name = "role_id"))])
    var roles: Set<MemberRole> = setOf()
}