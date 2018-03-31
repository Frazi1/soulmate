package com.soulmate.services

import com.soulmate.models.UserAccount
import com.soulmate.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    @Autowired private lateinit var userRepository: UserRepository
    fun addUser(u: UserAccount) {
        userRepository.save(u)
    }

    fun getUsers(): Iterable<UserAccount>{
        return userRepository.findAll()
    }

    fun getUser(id: Long): Optional<UserAccount> = userRepository.findById(id)

    fun updateUser(userAccount: UserAccount) = userRepository.save(userAccount)
}