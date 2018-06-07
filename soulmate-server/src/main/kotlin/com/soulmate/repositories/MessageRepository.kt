package com.soulmate.repositories

import com.soulmate.models.UserMessage
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

interface MessageRepository : PagingAndSortingRepository<UserMessage, Long>, JpaSpecificationExecutor<UserMessage>