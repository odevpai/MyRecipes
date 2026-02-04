package br.com.domain.entity

import java.time.Instant

abstract class Basic{
    abstract val id: String
    abstract val createdAt: Instant
}