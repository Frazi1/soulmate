package com.soulmate.services

import com.soulmate.models.mapping.toAccountEstimationDto
import com.soulmate.models.UserAccount
import com.soulmate.models.mapping.toExistingUserAccount
import com.soulmate.models.mapping.toUserAccountDto
import com.soulmate.repositories.UserRepository
import com.soulmate.validation.exceptions.UserDoesNotExistException
import dtos.AccountEstimationDto
import dtos.UserAccountDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun addUser(u: UserAccount) {
        userRepository.save(u)
    }

    fun getUsers(): Iterable<UserAccountDto> {
        return userRepository.findAll().map { it.toUserAccountDto() }
    }

    fun getUser(id: Long): UserAccountDto {
        return userRepository.findById(id).orElseThrow { UserDoesNotExistException(id) }
                .toUserAccountDto()
    }

    fun updateUser(userAccountDto: UserAccountDto) {
        val existingUser = userRepository.findById(userAccountDto.id)
                .orElseThrow { UserDoesNotExistException(userAccountDto.id) }
        val updatedUser: UserAccount = userAccountDto.toExistingUserAccount(existingUser)
        userRepository.save(updatedUser)
    }


    fun getUsersForEstimation(currentUserId: Long): Iterable<AccountEstimationDto> {
        val userAccountEntity = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        val alreadyEstimatedUserIds: List<Long> = userAccountEntity.likedCollection
                .map { it.id }
                .plus(currentUserId) //Add the current user to exclude him from the results
        val notEstimatedUsers = userRepository.findUserAccountsNotIn(alreadyEstimatedUserIds)
        return notEstimatedUsers.map { it.toAccountEstimationDto(userAccountEntity) }
    }

    fun addLikeEstimationForUserAccount(currentUserId: Long, likedUserId: Long) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        val likedUser = userRepository.findById(likedUserId).orElseThrow { UserDoesNotExistException(likedUserId) }
        currentUser.likedCollection.add(likedUser)
        userRepository.save(currentUser)
    }

    fun undoLikeEstimationForUserAccount(currentUserId: Long, unlikedUserId: Long) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        currentUser.likedCollection.removeIf { it.id == unlikedUserId }
        userRepository.save(currentUser)
    }
}