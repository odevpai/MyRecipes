package br.com.domain.model

import br.com.domain.entity.User

class UserFactory {
    fun create(user: UserFake) = when (user) {
        UserFake.Anna -> {
            User(
                name = "Anna",
                email = "anna@test.com",
                password = "123456A@",
                phone = "11 9 9999-9999"
            )
        }

        UserFake.Alex -> {
            User(
                name = "Alex",
                email = "alex@test.com",
                password = "123456A@",
                phone = "11 9 9999-9999"
            )
        }
    }

//    TODO: O que eh sealed class
    sealed class UserFake {
        data object Anna : UserFake()
        data object Alex : UserFake()
    }

}