package com.soulmate.repositories

import com.soulmate.models.UserMessage
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserMessageRepository : CrudRepository<UserMessage, Long> {
//    @Modifying
//    @Query("insert into UserMessage ()")
//    fun insertMessage(@Param("fromUserId") fromUserId: Long,
//                      @Param("toUserId") toUserId: Long,
//                      @Param("content") content: String)
}