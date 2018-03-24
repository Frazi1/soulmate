package com.soulmate.models

import javax.persistence.*

@Suppress("unused")
@Entity
@Table(name = "images")
class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "profile_order")
    var order: Int = 0

    @Column(name = "data")
    @Lob
    var data: ByteArray? = null

    @Column(name = "description")
    var description: String = ""

    @ManyToOne
    @JoinColumn(name = "user_id")
    var userAccount: UserAccount? = null

    constructor(order: Int, data: ByteArray?, description: String) {
        this.order = order
        this.data = data
        this.description = description
    }

    constructor()
}