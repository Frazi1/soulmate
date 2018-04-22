package com.soulmate.services

import com.soulmate.mapping.toAccountEstimationDto
import com.soulmate.models.UserAccount
import com.soulmate.repositories.UserRepository
import dtos.AccountEstimationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun addUser(u: UserAccount) {
        userRepository.save(u)
    }

    fun getUsers(): Iterable<UserAccount> {
        return userRepository.findAll()
    }

    fun getUser(id: Long): Optional<UserAccount> = userRepository.findById(id)

    fun updateUser(userAccount: UserAccount) = userRepository.save(userAccount)

    fun getUsersForEstimation(userId: Long): Iterable<AccountEstimationDto> {
        val userAccount = userRepository.findById(userId)
        val ids: List<Long> = userAccount.get().likedCollection
                .map { it.id }
                .plus(userId)
        val notEstimatedUsers = userRepository.findUserAccountsNotIn(ids)
        val map = notEstimatedUsers
                .map { it.toAccountEstimationDto(userAccount.get()) }
        return map
    }

    fun addLikeFromUserAccountTo(sourceId: Long, destinationId: Long) {
        val source = userRepository.findById(sourceId)
        val destination = userRepository.findById(destinationId)
        if (source.isPresent && destination.isPresent) {
            val sourceEntity = source.get()
            sourceEntity.likedCollection.add(destination.get())
            userRepository.save(sourceEntity)
        }
    }

    fun undoLikeEstimationForUserAccount(currentUserId: Long, userIdToRemove: Long) {
        val currentUser = userRepository.findById(currentUserId)
        currentUser.ifPresent {
            it.likedCollection.removeIf {it.id == userIdToRemove}
            userRepository.save(it)
        }
    }
}