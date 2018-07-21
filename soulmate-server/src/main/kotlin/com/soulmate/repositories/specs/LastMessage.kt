package com.soulmate.repositories.specs

import com.soulmate.models.dataAccess.UserMessage
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import javax.persistence.criteria.*

class LastMessage(private val preFilter: Specification<UserMessage> = TrueSpec()) : Specification<UserMessage> {
    override fun toPredicate(root: Root<UserMessage>, cq: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate {
        val dateSubQuery: Subquery<LocalDateTime> = cq.subquery(LocalDateTime::class.java)
        val subRoot: Root<UserMessage> = dateSubQuery.where(preFilter.toPredicate(root, cq, cb)).from(UserMessage::class.java)
        dateSubQuery.select(cb.greatest(subRoot.get<LocalDateTime>(UserMessage::sentTime.name)))
        return cb.equal(root.get<LocalDateTime>(UserMessage::sentTime.name), dateSubQuery)
    }
}