package com.example.jpatest.service

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import javax.persistence.EntityManagerFactory

@Service
class JpaTestService(
    @Qualifier("testEntityManagerFactory")
    private val entityManagerFactory: EntityManagerFactory
) {
    private val em = entityManagerFactory.createEntityManager()
    private val tx = em.transaction
    private val logger = KotlinLogging.logger { }

    fun test() {
    }
}
