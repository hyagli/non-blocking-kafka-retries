package com.hus.nonblockingkafkaretries.data.entity

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Message(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val topic: String,
    val text: String,
    @CreationTimestamp
    val created: Date? = null,
)
