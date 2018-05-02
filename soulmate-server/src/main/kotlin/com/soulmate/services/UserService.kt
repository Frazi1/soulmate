package com.soulmate.services

import com.soulmate.models.ProfileEstimation
import com.soulmate.models.UserAccount
import com.soulmate.models.mapping.toExistingUserAccount
import com.soulmate.models.mapping.toUserAccountDto
import com.soulmate.repositories.UserRepository
import com.soulmate.shared.Estimation
import com.soulmate.validation.exceptions.UserDoesNotExistException
import dtos.UserAccountDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var imageService: ImageService

    fun addUser(u: UserAccount) {
        userRepository.save(u)
    }

    fun getUsers(): Iterable<UserAccountDto> {
        return userRepository.findAll().map { it.toUserAccountDto() }
    }

    fun getUser(id: Long): UserAccountDto {
        val userAccount = userRepository.findById(id).orElseThrow { UserDoesNotExistException(id) }
        val userAccountDto = userAccount.toUserAccountDto()
        userAccountDto.profileImages = userAccountDto.profileImages.sortedBy { it.isMainImage }
        return userAccountDto
    }

    fun updateUser(userAccountDto: UserAccountDto) {
        val existingUser = userRepository.findById(userAccountDto.id)
                .orElseThrow { UserDoesNotExistException(userAccountDto.id) }
        val updatedUser: UserAccount = userAccountDto.toExistingUserAccount(existingUser)
        userRepository.save(updatedUser)
    }


    fun getUsersForEstimation(currentUserId: Long): Iterable<UserAccountDto> {
        val userAccountEntity = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        val alreadyEstimatedUserIds: List<Long> = userAccountEntity.estimationCollection
                .mapNotNull { it.destinationUserAccount }
                .map { it.id }
                .plus(currentUserId) //Add the current user to exclude him from the results
        val notEstimatedUsers = userRepository.findUserAccountsNotIn(alreadyEstimatedUserIds)
        return notEstimatedUsers.map { it.toUserAccountDto() }
    }

    fun addUserEstimation(currentUserId: Long, likedUserId: Long, estimation: Estimation): Long {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        val likedUser = userRepository.findById(likedUserId).orElseThrow { UserDoesNotExistException(likedUserId) }
        val profileEstimation = ProfileEstimation(currentUser, likedUser, estimation)
        currentUser.estimationCollection.add(profileEstimation)
        userRepository.save(currentUser)
        return profileEstimation.id
    }

    fun removeUserEstimations(currentUserId: Long, userIdsToRemove: Iterable<Long>) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        removeUserEstimationsInternal(currentUser, userIdsToRemove)
    }

    @Transactional
    fun removeAllUserEstimations(currentUserId: Long) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        removeUserEstimationsInternal(
                currentUser,
                currentUser.estimationCollection
                        .mapNotNull { it.destinationUserAccount }
                        .map { it.id })
    }

    private fun removeUserEstimationsInternal(currentUserAccount: UserAccount, userIdsToRemove: Iterable<Long>) {
        currentUserAccount.estimationCollection.removeIf {
            userIdsToRemove.contains(it.destinationUserAccount!!.id)
        }
        userRepository.save(currentUserAccount)
    }
}