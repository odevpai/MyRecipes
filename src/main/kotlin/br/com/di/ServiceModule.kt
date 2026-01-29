package br.com.di

import br.com.domain.services.AddUserService
import org.koin.dsl.module

object ServiceModule {
    val module = module {
        single<AddUserService> { AddUserService(get(), get()) }
    }
}