package com.soulmate.shared.dtos

import com.soulmate.shared.dtos.interfaces.IImage

class UploadImageDto(var order: Int = 1,
                     override var data: ByteArray? = ByteArray(DEFAULT_BUFFER_SIZE),
                     var description: String = "",
                     var isMainImage: Boolean = false) : IImage