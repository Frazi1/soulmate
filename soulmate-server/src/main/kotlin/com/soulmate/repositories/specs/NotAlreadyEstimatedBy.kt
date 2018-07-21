package com.soulmate.repositories.specs

import com.soulmate.models.dataAccess.UserAccount
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class NotAlreadyEstimatedBy(userAccount: UserAccount) : Specification<UserAccount> {
    private val estimatedUserIds: List<Long> = userAccount
            .estimationCollection
            .mapNotNull { it.destinationUserAccount }
            .map { it.id }
            .plus(userAccount.id)

    override fun toPredicate(root: Root<UserAccount>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate {
        return cb.not(root.get<Int>("id").`in`(estimatedUserIds))
    }
}