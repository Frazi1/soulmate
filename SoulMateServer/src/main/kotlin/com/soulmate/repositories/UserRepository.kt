package com.soulmate.repositories

import com.soulmate.models.UserAccount
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserAccount, Long> {
}