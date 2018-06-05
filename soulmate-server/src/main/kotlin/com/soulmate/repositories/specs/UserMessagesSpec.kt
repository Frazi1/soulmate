package com.soulmate.repositories.specs

import com.soulmate.models.UserAccount
import com.soulmate.models.UserMessage
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class UserMessagesSpec(private val firstUserId: Long,
                       private val secondUserId: Long) : Specification<UserMessage> {
    override fun toPredicate(root: Root<UserMessage>, cq: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? {
        val p1 = cb.and(
                cb.equal(root.get<UserAccount>(UserMessage::sourceUserAccount.name).get<Long>(UserAccount::id.name), firstUserId),
                cb.equal(root.get<UserAccount>(UserMessage::destinationUserAccount.name).get<Long>(UserAccount::id.name), secondUserId)
        )
        val p2 = cb.and(
                cb.equal(root.get<UserAccount>(UserMessage::sourceUserAccount.name).get<Long>(UserAccount::id.name), secondUserId),
                cb.equal(root.get<UserAccount>(UserMessage::destinationUserAccount.name).get<Long>(UserAccount::id.name), firstUserId)
        )
        return cb.or(p1, p2)
    }
}