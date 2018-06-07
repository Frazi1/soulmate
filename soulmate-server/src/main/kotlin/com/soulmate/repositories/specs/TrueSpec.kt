package com.soulmate.repositories.specs

import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class TrueSpec<T> : Specification<T> {
    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate {
        return cb.and()
    }
}