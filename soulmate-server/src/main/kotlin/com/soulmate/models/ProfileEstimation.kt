package com.soulmate.models

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "profile_estimations")
class ProfileEstimation() : Serializable {

    @Id
    var id: Long = 0

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "source_user_id")
    var sourceUserAccount: UserAccount? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "destination_user_id")
    var destinationUserAccount: UserAccount? = null

    @Column(name = "estimation")
    var estimation: Estimation = Estimation.NONE

    constructor(sourceId: UserAccount, destId: UserAccount, estimation: Estimation) : this() {
        this.sourceUserAccount = sourceId
        this.destinationUserAccount = destId
        this.estimation = estimation
    }
}