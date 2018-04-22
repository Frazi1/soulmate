package com.soulmate.services

import com.soulmate.mapping.toAccountEstimationDto
import com.soulmate.models.UserAccount
import com.soulmate.repositories.UserRepository
import com.soulmate.validation.exceptions.SoulmateUserDoesNotExistException
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

    fun getUsersForEstimation(currentUserId: Long): Iterable<AccountEstimationDto> {
        val userAccountEntity = userRepository.findById(currentUserId).orElseThrow { SoulmateUserDoesNotExistException(currentUserId) }
        val alreadyEstimatedUserIds: List<Long> = userAccountEntity.likedCollection
                .map { it.id }
                .plus(currentUserId) //Add the current user to exclude him from the results
        val notEstimatedUsers = userRepository.findUserAccountsNotIn(alreadyEstimatedUserIds)
        return notEstimatedUsers.map { it.toAccountEstimationDto(userAccountEntity) }
    }

    fun addLikeEstimationForUserAccount(currentUserId: Long, likedUserId: Long) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { SoulmateUserDoesNotExistException(currentUserId) }
        val likedUser = userRepository.findById(likedUserId).orElseThrow { SoulmateUserDoesNotExistException(likedUserId) }
        currentUser.likedCollection.add(likedUser)
        userRepository.save(currentUser)
    }

    fun undoLikeEstimationForUserAccount(currentUserId: Long, unlikedUserId: Long) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { SoulmateUserDoesNotExistException(currentUserId) }
        currentUser.likedCollection.removeIf { it.id == unlikedUserId }
        userRepository.save(currentUser)
    }
}