package com.soulmate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan()
class SoulmateApplication {

    fun main(args: Array<String>) {
        runApplication<SoulmateApplication>(*args)
    }
}
