package com.hus.nonblockingkafkaretries.controllers

import com.google.gson.Gson
import com.hus.nonblockingkafkaretries.data.repo.MessageRepo
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val messageRepo: MessageRepo,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    @GetMapping("/get-messages")
    fun getMessages(): String {
        val allMessages = messageRepo.findAll()
        val data = allMessages.groupBy {
            it.topic
        }.map { entry ->
            object {
                val topic = entry.key
                val messages = entry.value
            }
        }
        return Gson().toJson(data)
    }

    @PostMapping("/send-message")
    fun sendMessage(message: String) {
        println("Message received: $message")
        kafkaTemplate.send(
            "my-queue",
            message
        )
    }
}
