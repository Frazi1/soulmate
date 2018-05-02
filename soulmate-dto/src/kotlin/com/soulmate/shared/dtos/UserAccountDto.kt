package com.soulmate.shared.dtos

import com.soulmate.shared.GenderType

open class UserAccountDto(var id: Long = 0,
                          var firstName: String? = "",
                          var lastName: String? = "",
                          var profileImages: Collection<ProfileImageDto> = listOf(),
                          var gender: GenderType = GenderType.NotDefined,
                          var personalStory: String = "")

