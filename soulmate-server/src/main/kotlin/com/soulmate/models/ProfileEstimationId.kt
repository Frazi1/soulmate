package com.soulmate.models

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class ProfileEstimationId : Serializable {
//    var sourceUserAccount: UserAccount? = null
    var sourceUserAccountId: Long = 0
//    var destinationUserAccount: UserAccount? = null
    var destinationUserAccountId: Long = 0
}