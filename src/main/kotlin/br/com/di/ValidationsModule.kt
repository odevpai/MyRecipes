package br.com.di

import br.com.domain.validations.AddValidationUserRequest
import br.com.domain.validations.AddValidationUserRequestImpl
import org.koin.dsl.module

object ValidationsModule {
    val module = module {
        single<AddValidationUserRequest> {
            AddValidationUserRequestImpl()
        }
    }
}