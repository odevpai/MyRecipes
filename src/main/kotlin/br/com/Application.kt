package br.com

import br.com.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureDI()
    configureSecurity()
    configureHTTP()
    configureStatusPage()
    configureMonitoring()
    configureSockets()
    configureRouting()
}
