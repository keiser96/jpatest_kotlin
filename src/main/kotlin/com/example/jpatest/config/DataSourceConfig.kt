package com.example.jpatest.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.example.jpatest"],
    entityManagerFactoryRef = "testEntityManagerFactory",
    transactionManagerRef = "testTransactionManager"
)
class DataSourceConfig {
    @Primary
    @Bean(name = ["testDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun dataSource(): DataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

    @Primary
    @Bean(name = ["testEntityManagerFactory"])
    fun entityManagerFactory(
        @Qualifier("testDataSource") testDataSource: DataSource,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean =
        builder.dataSource(this.dataSource())
            .packages("com.example.jpatest")
            .persistenceUnit("hikari")
            .build()

    @Primary
    @Bean(name = ["testTransactionManager"])
    fun transactionManager(@Qualifier("testEntityManagerFactory") entityManagerFactory: EntityManagerFactory) =
        JpaTransactionManager(entityManagerFactory)
}
