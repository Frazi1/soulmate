package com.soulmate.repositories

import com.soulmate.models.UserAccount
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<UserAccount, Long>, JpaSpecificationExecutor<UserAccount> {
    @Query("SELECT u FROM UserAccount u WHERE u.id NOT IN :ids")
    fun findUserAccountsNotIn(@Param("ids") ids: Collection<Long>): Collection<UserAccount>
}