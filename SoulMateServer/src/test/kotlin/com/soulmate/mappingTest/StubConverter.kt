package com.soulmate.mappingTest

import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface StubConverter {
    @Mapping(source = "stub1", target = "stub1")
    fun toStub2(stub1: Stub1): Stub2
}