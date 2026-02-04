package br.com.domain.entity

import org.bson.BsonType
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonRepresentation
import org.bson.types.ObjectId
import java.time.Instant

data class User(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    override val id: String = ObjectId().toHexString(),
    override val createdAt: Instant = Instant.now()
) : Basic()
