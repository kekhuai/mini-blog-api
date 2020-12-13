package me.kekhuay.miniblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MiniBlogApplication

fun main(args: Array<String>) {
    runApplication<MiniBlogApplication>(*args)
}
