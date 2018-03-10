package com.soulmate.models

import javax.persistence.*

@Entity
@Table(name = "members")
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
}