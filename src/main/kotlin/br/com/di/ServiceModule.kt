package br.com.di

import br.com.domain.services.password.BCryptPasswordService
import br.com.domain.services.user.AddUserService
import org.koin.dsl.module

object ServiceModule {
    val module = module {
        single<AddUserService> { AddUserService(
            get(),
            get(),
            get(),
            get())
        }
        single<BCryptPasswordService> { BCryptPasswordService() }
    }
}