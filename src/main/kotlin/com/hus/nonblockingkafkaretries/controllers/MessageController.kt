package com.hus.nonblockingkafkaretries.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController {
    @GetMapping("/get-messages")
    fun getMessages(): String {
        return """
            {
                "consumer1": [
                    {
                        "timestamp": "03:29:01.123",
                        "message": "First"
                    },
                    {
                        "timestamp": "03:29:01.123",
                        "message": "Second"
                    }
                ],
                "consumer2": [
                    {
                        "timestamp": "03:29:01.123",
                        "message": "First 2"
                    },
                    {
                        "timestamp": "03:29:01.123",
                        "message": "Second 2"
                    }
                ],
                "consumer3": [
                    {
                        "timestamp": "03:29:01.123",
                        "message": "First 3"
                    },
                    {
                        "timestamp": "03:29:01.123",
                        "message": "Second 3"
                    }
                ]
            }
            """.trimIndent()
    }
}