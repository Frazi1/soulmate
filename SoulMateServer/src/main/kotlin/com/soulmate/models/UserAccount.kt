package com.soulmate.models

import javax.persistence.*

@Entity
@Table(name = "user_account")
class UserAccount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @JoinColumn(name = "member_id")
    @OneToOne(cascade = [(CascadeType.PERSIST)])
    var member: Member? = null

    @Column(name = "first_name")
    var firstName: String? = null

    @Column(name = "last_name")
    var lastName: String? = null

    constructor(id: Long, member: Member?, firstName: String, lastName: String?)
            : this(firstName, lastName) {
        this.id = id
        this.member = member
    }

    constructor(firstName: String, lastName: String? = null) {
        this.firstName = firstName
        this.lastName = lastName
    }

    constructor()
}