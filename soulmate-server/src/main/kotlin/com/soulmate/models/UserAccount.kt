package com.soulmate.models

import com.soulmate.shared.GenderType
import javax.persistence.*


@Entity
@Table(name = "user_account")
class UserAccount() {

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

    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    var profileImages: MutableCollection<ProfileImage> = mutableListOf()

    @OneToMany(mappedBy = "sourceUserAccount", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var estimationCollection: MutableCollection<ProfileEstimation> = mutableListOf()

    @OneToMany(mappedBy = "destinationUserAccount", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var estimatedByCollection: MutableCollection<ProfileEstimation> = mutableListOf()

    constructor(id: Long, member: Member?, firstName: String?, lastName: String?, gender: GenderType, personalStory: String)
            : this(firstName, lastName) {
        this.id = id
        this.member = member
        this.gender = gender
        this.personalStory = personalStory
    }

    constructor(firstName: String?, lastName: String? = null) : this() {
        this.firstName = firstName
        this.lastName = lastName
    }
}