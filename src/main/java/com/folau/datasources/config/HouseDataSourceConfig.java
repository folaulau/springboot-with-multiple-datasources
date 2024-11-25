package com.folau.datasources.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.folau.datasources.house.repository", // Repository package for house
        entityManagerFactoryRef = "houseEntityManagerFactory", // Reference to EntityManagerFactory
        transactionManagerRef = "houseTransactionManager" // Reference to TransactionManager
)
public class HouseDataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.house")
    public DataSourceProperties houseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource houseDataSource() {
        return houseDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean houseEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(houseDataSource());
        factory.setPackagesToScan("com.folau.datasources.house.entity"); // Entity package for house
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create");
        return factory;
    }

    @Primary
    @Bean
    public PlatformTransactionManager houseTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(houseEntityManagerFactory().getObject());
        return transactionManager;
    }
}