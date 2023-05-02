package com.example.jpatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class JpatestApplication

fun main(args: Array<String>) {
    runApplication<JpatestApplication>(*args)
}
