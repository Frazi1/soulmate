package com.soulmate.utils.extensions

import com.soulmate.services.helpers.ImageCompressor
import dtos.interfaces.IImage

fun IImage.resize(size: Int) {
    if (data != null)
        data = ImageCompressor.resize(data!!, size)
}