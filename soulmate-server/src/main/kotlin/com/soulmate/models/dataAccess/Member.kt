package com.soulmate.models.dataAccess

import javax.persistence.*

@Entity
@Table(name = "member")
class Member() {

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

    @OneToOne(cascade = [(CascadeType.PERSIST)])
    var userAccount: UserAccount? = null

    constructor(email: String, passwordHash: String, userAccount: UserAccount?)
            : this() {
        this.email = email
        this.passwordHash = passwordHash
        this.userAccount = userAccount
    }
}