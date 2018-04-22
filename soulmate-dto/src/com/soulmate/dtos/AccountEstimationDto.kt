package dtos

class AccountEstimationDto(
        id: Long = 0,
        firstName: String? = "",
        lastName: String? = "",
        profileImages: List<ProfileImageDto> = listOf(),
        gender: GenderType = GenderType.NotDefined,
        personalStory: String = "",
        var hasAlreadyLiked: Boolean
) : UserAccountDto(id, firstName, lastName, profileImages, gender, personalStory)