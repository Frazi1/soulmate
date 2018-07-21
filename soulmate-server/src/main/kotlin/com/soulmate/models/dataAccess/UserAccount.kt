package com.soulmate.models.dataAccess

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

    @Column(name = "age", nullable = false)
    var age: Int = 0

    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    var profileImages: MutableCollection<ProfileImage> = mutableListOf()

    @OneToMany(mappedBy = "sourceUserAccount", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var estimationCollection: MutableCollection<ProfileEstimation> = mutableListOf()

    @OneToMany(mappedBy = "destinationUserAccount", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var estimatedByCollection: MutableCollection<ProfileEstimation> = mutableListOf()

    @OneToMany(mappedBy = "sourceUserAccount", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var sentMessages: MutableCollection<UserMessage> = mutableListOf()

    @OneToMany(mappedBy = "destinationUserAccount", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var receivedMessages: MutableCollection<UserMessage> = mutableListOf()

    val fullName: String
        get() = "$firstName $lastName"


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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserAccount

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}