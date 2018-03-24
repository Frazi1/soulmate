package dtos

data class ProfileImageDto(var order: Int = 1,
                           var data: ByteArray? = null,
                           var description: String = "")