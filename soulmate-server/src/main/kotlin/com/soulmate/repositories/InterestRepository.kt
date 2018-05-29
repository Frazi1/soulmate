package com.soulmate.repositories

import com.soulmate.models.Interest
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository

interface InterestRepository : CrudRepository<Interest, Long>, JpaSpecificationExecutor<Interest>