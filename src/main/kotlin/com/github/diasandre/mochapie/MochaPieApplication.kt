package com.github.diasandre.mochapie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MochaPieApplication

fun main(args: Array<String>) {
    runApplication<MochaPieApplication>(*args)
}
