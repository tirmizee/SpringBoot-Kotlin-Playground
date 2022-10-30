package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootDockerMultiStageApplication

fun main(args: Array<String>) {
    runApplication<SpringBootDockerMultiStageApplication>(*args)
}
