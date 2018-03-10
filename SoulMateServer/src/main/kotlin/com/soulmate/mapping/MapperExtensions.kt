package com.soulmate.mapping

import org.modelmapper.ModelMapper
import org.modelmapper.TypeMap

inline fun <reified T> ModelMapper.map(o: Any): T {
    return map(o, T::class.java)
}

inline fun <reified T, reified U> ModelMapper.createTypeMap(): TypeMap<T, U> {
    return createTypeMap(T::class.java, U::class.java)
}