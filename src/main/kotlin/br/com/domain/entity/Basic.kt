package br.com.domain.entity

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

abstract class Basic{
    abstract val id: String
    @OptIn(ExperimentalTime::class)
    abstract val createdAt: Instant
}