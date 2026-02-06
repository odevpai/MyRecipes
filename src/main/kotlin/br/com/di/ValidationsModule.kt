package br.com.di

import br.com.domain.validations.AddUserRequestValidation
import br.com.domain.validations.AddUserRequestValidationImpl
import br.com.domain.validations.AuthUserRequestValidation
import br.com.domain.validations.AuthUserRequestValidationImpl
import org.koin.dsl.module

object ValidationsModule {
    val module = module {
        single<AddUserRequestValidation> {
            AddUserRequestValidationImpl()
        }

        single<AuthUserRequestValidation> {
            AuthUserRequestValidationImpl()
        }

    }
}