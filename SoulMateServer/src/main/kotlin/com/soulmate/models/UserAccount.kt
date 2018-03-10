package com.soulmate.models

import com.soulmate.dtos.UserAccountDto
import javax.persistence.*

@Entity
@Table(name = "user_accounts")
class UserAccount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        get
        set

    @JoinColumn(name = "member_id")
    @ManyToOne
    var member: Member? = null
        get
        set

    @Column(name="first_name")
    var firstName: String = ""

    @Column(name="last_name")
    var lastName: String? = null

    constructor(id: Long, member: Member?, firstName: String, lastName: String?) {
        this.id = id
        this.member = member
        this.firstName = firstName
        this.lastName = lastName
    }
}