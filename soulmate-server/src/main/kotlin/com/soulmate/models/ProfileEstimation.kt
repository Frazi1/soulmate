package com.soulmate.models

import java.io.Serializable
import javax.persistence.*

@Entity
//@IdClass(ProfileEstimationId::class)
@Table(name = "profile_estimations")
class ProfileEstimation() : Serializable {

//    @Id
//    @ManyToOne(cascade = [CascadeType.ALL])
//    var sourceUserAccountId: Long = 0
    //
//    @Id
//    @ManyToOne(cascade = [CascadeType.ALL])
//    var destinationUserAccountId: Long = 0

//    @EmbeddedId
//    lateinit var profileEstimationId: ProfileEstimationId

    @Id
    var id: Long = 0

//    @Id
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "source_user_id")
//    @JoinColumn(/*name = "source_user_account_id",*/ referencedColumnName = "id")
    var sourceUserAccount: UserAccount? = null

//    @Id
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "destination_user_id")
//    @JoinColumn(/*name = "destination_user_account_id",*/ referencedColumnName = "id")
    var destinationUserAccount: UserAccount? = null

    @Column(name = "estimation")
    var estimation: Estimation = Estimation.NONE

    //    constructor(sourceUserAccount: UserAccount, destinationUserAccount: UserAccount, estimation: Estimation) : this() {
//        this.sourceUserAccount = sourceUserAccount
//        this.destinationUserAccount = destinationUserAccount
//        this.estimation = estimation
//    }
    constructor(sourceId: UserAccount, destId: UserAccount, estimation: Estimation) : this() {
        this.sourceUserAccount = sourceId
        this.destinationUserAccount = destId
        this.estimation = estimation
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProfileEstimation

//        if (sourceUserAccount != other.sourceUserAccount) return false
//        if (destinationUserAccount != other.destinationUserAccount) return false
        if (estimation != other.estimation) return false


//
//        if (sourceUserAccount != null) {
//            if(sourceUserAccount!!.id != other.sourceUserAccount!!.id)
//                return false
//        }
//
//        if (destinationUserAccount != null) {
//            if(destinationUserAccount!!.id != other.destinationUserAccount!!.id)
//                return false
//        }

        return true
    }

//    override fun hashCode(): Int {
//        var result = sourceUserAccount?.hashCode() ?: 0
//        result = 31 * result + (destinationUserAccount?.hashCode() ?: 0)
//        result = 31 * result + estimation.hashCode()
//        return result
//    }
}