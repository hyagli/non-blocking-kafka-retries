package com.hus.nonblockingkafkaretries

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NonBlockingKafkaRetriesApplication

fun main(args: Array<String>) {
    runApplication<NonBlockingKafkaRetriesApplication>(*args)
}
