package dtos

class ProfileEstimationDto(
        id: Long = 0,
        firstName: String? = "",
        lastName: String? = "",
        profileImages: List<ProfileImageDto> = listOf(),
        gender: GenderType = GenderType.NotDefined,
        personalStory: String = "",
        var hasAlreadyLiked: Boolean = false
) : UserAccountDto(id, firstName, lastName, profileImages, gender, personalStory)