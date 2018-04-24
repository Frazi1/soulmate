package com.soulmate.repositories

import com.soulmate.models.ProfileEstimation
import com.soulmate.models.ProfileEstimationId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileEstimationRepository : CrudRepository<ProfileEstimation, ProfileEstimationId> {
    fun deleteAllBySourceUserAccountId(userAccountIds: Collection<Long>)
}