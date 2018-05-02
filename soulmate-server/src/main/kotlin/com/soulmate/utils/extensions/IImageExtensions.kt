package com.soulmate.utils.extensions

import com.soulmate.shared.dtos.interfaces.IImage
import com.soulmate.services.helpers.ImageCompressor

fun IImage.resize(size: Int) {
    if (data != null)
        data = ImageCompressor.resize(data!!, size)
}