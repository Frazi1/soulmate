package com.soulmate.models

import javax.persistence.*

@Entity
@Table(name = "member")
class Member {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "email")
    var email: String = ""

    @Column(name = "password_hash")
    var passwordHash: String = ""

    @ManyToMany()
    @JoinTable(name = "member_roles",
            joinColumns = [(JoinColumn(name = "member_id"))],
            inverseJoinColumns = [(JoinColumn(name = "role_id"))])
    var roles: Set<MemberRole> = setOf()

    @OneToOne
    var userAccount: UserAccount?

    constructor(email: String, passwordHash: String, userAccount: UserAccount?) {
        this.email = email
        this.passwordHash = passwordHash
        this.userAccount = userAccount
    }
}