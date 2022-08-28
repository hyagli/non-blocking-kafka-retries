package com.hus.nonblockingkafkaretries.controllers

import com.hus.nonblockingkafkaretries.data.entity.Message
import com.hus.nonblockingkafkaretries.data.repo.MessageRepo
import org.springframework.kafka.annotation.DltHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.retry.annotation.Backoff
import org.springframework.stereotype.Service

@Service
class KafkaConsumers(
    private val messageRepo: MessageRepo
) {

    @RetryableTopic(attempts = "4", backoff = Backoff(1000, multiplier = 1.5))
    @KafkaListener(topics = ["my-queue"])
    fun consumer1(
        message: String,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String
    ) {
        messageRepo.save(
            Message(
                text = message,
                topic = topic
            )
        )
        throw RuntimeException("Cowabunga")
    }

    @DltHandler
    @KafkaListener(topics = ["my-queue-dlt"])
    fun consumerDlt(
        message: String,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String
    ) {
        messageRepo.save(
            Message(
                text = message,
                topic = topic
            )
        )
    }
}
