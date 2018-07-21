package com.soulmate.repositories.specs

import com.soulmate.models.dataAccess.ProfileEstimation
import com.soulmate.models.dataAccess.UserAccount
import com.soulmate.shared.Estimation
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.*

class IsPairOf(private val id: Long) : Specification<UserAccount> {
    override fun toPredicate(root: Root<UserAccount>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate {
        val estimations: Join<UserAccount, ProfileEstimation> = root.join<UserAccount, ProfileEstimation>(UserAccount::estimationCollection.name)
        val equal = cb.equal(estimations.get<Estimation>(ProfileEstimation::estimation.name), Estimation.LIKE)
        val user = cb.equal(estimations.get<Long>(ProfileEstimation::destinationUserAccount.name), id)
        return equal

    }
}