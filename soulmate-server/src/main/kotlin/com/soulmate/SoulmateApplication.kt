package com.soulmate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import java.util.TimeZone
import javax.annotation.PostConstruct


@SpringBootApplication
@ComponentScan()
class SoulmateApplication {

    @PostConstruct
    fun setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<SoulmateApplication>(*args)
        }
    }
}
