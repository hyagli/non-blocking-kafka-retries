package com.hus.nonblockingkafkaretries.data.repo

import com.hus.nonblockingkafkaretries.data.entity.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepo : JpaRepository<Message, Long>
