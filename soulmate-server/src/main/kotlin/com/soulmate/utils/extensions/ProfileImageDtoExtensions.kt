package com.soulmate.utils.extensions

import dtos.ProfileImageDto

fun ProfileImageDto.getThumbnail(size: Int): ProfileImageDto {
    val clone = clone()
    clone.resize(size)
    return clone
}

fun ProfileImageDto.clone(): ProfileImageDto {
    return ProfileImageDto(order, data, description, isMainImage)
}