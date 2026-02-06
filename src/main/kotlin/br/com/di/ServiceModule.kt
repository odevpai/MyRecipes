package br.com.di

import br.com.domain.services.password.BCryptPasswordService
import br.com.domain.services.token.TokenService
import br.com.domain.services.user.AddUserService
import br.com.domain.services.user.GetUserByIdService
import br.com.domain.services.user.GetUserProfileService
import br.com.domain.services.user.LoginUserService
import org.koin.dsl.module

object ServiceModule {
    val module = module {
        single<AddUserService> {
            AddUserService(
                get(),
                get(),
                get(),
                get()
            )
        }

        single<LoginUserService> {
            LoginUserService(
                get(),
                get(),
                get(),
                get()
            )
        }

        single<GetUserProfileService> { GetUserProfileService(get()) }

        single<GetUserByIdService> { GetUserByIdService(get()) }

        single<TokenService> { TokenService() }

        single<BCryptPasswordService> { BCryptPasswordService() }
    }
}