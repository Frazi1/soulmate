package com.soulmate.models

import javax.persistence.*

@Entity
@Table(name = "interest")
class Interest : IEntityWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    override var id: Long = 0

    @Column(name = "title")
    var title: String = ""

    @Column(name = "description")
    var description: String = ""

    @ManyToMany(mappedBy = "interests")
    var users: MutableCollection<UserAccount> = mutableListOf()
}