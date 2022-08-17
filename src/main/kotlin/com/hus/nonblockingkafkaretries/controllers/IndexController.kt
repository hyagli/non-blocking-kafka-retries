package com.hus.nonblockingkafkaretries.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    @GetMapping("/")
    fun index(model: Model): String {
        model["title"] = "Non-blocking Kafka Retries"
        return "index"
    }
}
