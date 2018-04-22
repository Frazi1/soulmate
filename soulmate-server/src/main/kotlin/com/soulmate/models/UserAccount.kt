package com.soulmate.models

import dtos.GenderType
import org.apache.catalina.User
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

    @Column(name = "gender")
    var gender: GenderType = GenderType.NotDefined

    @Column(name = "personal_story")
    var personalStory: String = ""

    @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER)
    var profileImages: Set<ProfileImage> = setOf()

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_likes",
            joinColumns = [(JoinColumn(name = "like_source", referencedColumnName = "id"))],
            inverseJoinColumns = [(JoinColumn(name = "like_destinantion", referencedColumnName = "id"))])
    var likedCollection: MutableCollection<UserAccount> = mutableListOf()

    @ManyToMany(mappedBy = "likedCollection", fetch = FetchType.EAGER)
    var likedByCollection: MutableCollection<UserAccount> = mutableListOf()


    constructor(id: Long, member: Member?, firstName: String?, lastName: String?, gender: GenderType, personalStory: String)
            : this(firstName, lastName) {
        this.id = id
        this.member = member
        this.gender = gender
        this.personalStory = personalStory
    }

    constructor(firstName: String?, lastName: String? = null) {
        this.firstName = firstName
        this.lastName = lastName
    }

    constructor()
}