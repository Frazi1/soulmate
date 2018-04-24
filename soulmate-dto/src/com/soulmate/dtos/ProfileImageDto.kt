package dtos

import dtos.interfaces.IImage

data class ProfileImageDto(var order: Int = 1,
                           override var data: ByteArray? = ByteArray(DEFAULT_BUFFER_SIZE),
                           var description: String = "",
                           var isMainImage: Boolean = false) : IImage