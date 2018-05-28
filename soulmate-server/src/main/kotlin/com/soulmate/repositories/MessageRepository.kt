package com.soulmate.repositories

import com.soulmate.models.UserMessage
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<UserMessage, Long>, JpaSpecificationExecutor<UserMessage>