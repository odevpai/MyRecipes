package br.com.domain.entity

import org.bson.BsonType
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonRepresentation
import org.bson.types.ObjectId
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class User @OptIn(ExperimentalTime::class) constructor(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    override val id: String = ObjectId().toHexString(),
    override val createdAt: Instant = Clock.System.now()
) : Basic()
