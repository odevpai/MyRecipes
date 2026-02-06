package br.com.application.payloads.mappers

import br.com.application.payloads.response.UserResponse
import br.com.domain.entity.User

fun User?.toUserResponse(): UserResponse {
    return UserResponse(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        email = this?.email.orEmpty(),
        phone = this?.phone.orEmpty(),
        createdAt = this?.createdAt.toString()
    )
}
