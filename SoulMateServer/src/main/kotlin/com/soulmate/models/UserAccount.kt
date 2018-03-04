package com.soulmate.models

import javax.persistence.*

@Entity()
@Table(name="test")
class UserAccount(name: String) {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var name = name
}