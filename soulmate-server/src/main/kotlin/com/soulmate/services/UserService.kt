package com.soulmate.services

import com.soulmate.models.Estimation
import com.soulmate.models.ProfileEstimation
import com.soulmate.models.UserAccount
import com.soulmate.models.mapping.toAccountEstimationDto
import com.soulmate.models.mapping.toExistingUserAccount
import com.soulmate.models.mapping.toUserAccountDto
import com.soulmate.repositories.ProfileEstimationRepository
import com.soulmate.repositories.UserRepository
import com.soulmate.validation.exceptions.UserDoesNotExistException
import dtos.ProfileEstimationDto
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

    @Autowired
    private lateinit var profileEstimationRepository: ProfileEstimationRepository

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


    fun getUsersForEstimation(currentUserId: Long): Iterable<ProfileEstimationDto> {
        val userAccountEntity = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        val alreadyEstimatedUserIds: List<Long> = userAccountEntity.estimationCollection
                .mapNotNull { it.destinationUserAccount }
                .map { it.id }
                .plus(currentUserId) //Add the current user to exclude him from the results
        val notEstimatedUsers = userRepository.findUserAccountsNotIn(alreadyEstimatedUserIds)
        return notEstimatedUsers.map { it.toAccountEstimationDto(userAccountEntity) }
    }

    //    @Transactional
    fun addLikeEstimationForUserAccount(currentUserId: Long, likedUserId: Long) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        val likedUser = userRepository.findById(likedUserId).orElseThrow { UserDoesNotExistException(likedUserId) }
        val estimation = ProfileEstimation(currentUser, likedUser, Estimation.LIKE)
        currentUser.estimationCollection.add(estimation)

        userRepository.save(currentUser)
    }

    fun undoLikeEstimationForUserAccount(currentUserId: Long, unlikedUserIds: Iterable<Long>) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        undoLikeEstimationForUserAccountInternal(currentUser, unlikedUserIds)
    }

    @Transactional
    fun undoAllLikeEstimationsForUserAccount(currentUserId: Long) {
        val currentUser = userRepository.findById(currentUserId).orElseThrow { UserDoesNotExistException(currentUserId) }
        undoLikeEstimationForUserAccountInternal(
                currentUser,
                currentUser.estimationCollection
                        .mapNotNull(ProfileEstimation::destinationUserAccount)
                        .map(UserAccount::id)
        )
    }

    private fun undoLikeEstimationForUserAccountInternal(userAccount: UserAccount, unlikedUserIds: Iterable<Long>) {
        userAccount.estimationCollection.removeIf {
            unlikedUserIds.contains(it.destinationUserAccount!!.id)
        }
        userRepository.save(userAccount)
    }
}