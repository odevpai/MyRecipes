package br.com.di

import com.mongodb.kotlin.client.coroutine.MongoClient
import org.koin.dsl.module

object DataBaseModule {

    val module = module {
        single {
            val client = MongoClient.create("mongodb://localhost:27017/")
            client.getDatabase("my_recipes")
        }
    }

}