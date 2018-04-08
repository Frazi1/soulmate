package dtos

data class UserAccountDto(var id: Long = 0,
                          var firstName: String? = "",
                          var lastName: String? = "",
                          var profileImages: List<ProfileImageDto> = listOf(),
                          var gender: GenderType = GenderType.NotDefined,
                          var personalStory: String = "")

