package com.soulmate.utils.extensions

import com.soulmate.shared.dtos.UploadImageDto

fun UploadImageDto.getThumbnail(size: Int): UploadImageDto {
    val clone = clone()
    clone.resize(size)
    return clone
}

fun UploadImageDto.clone(): UploadImageDto {
    return UploadImageDto(order, data, description, isMainImage)
}