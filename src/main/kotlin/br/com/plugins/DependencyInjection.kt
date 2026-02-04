package br.com.plugins

import br.com.di.DataBaseModule
import br.com.di.RepositoryModule
import br.com.di.ServiceModule
import br.com.di.ValidationsModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {

    val module = DataBaseModule.module +
            RepositoryModule.module +
            ServiceModule.module +
            ValidationsModule.module

    install(Koin) {
        slf4jLogger()
        modules(module)
    }
}