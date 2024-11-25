package com.folau.datasources.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        entityManagerFactoryRef = "houseEntityManagerFactory",   // Reference to EntityManagerFactory
        transactionManagerRef = "houseTransactionManager"        // Reference to TransactionManager
)
public class HouseDataSourceConfig {

    /**
     * Configuration for House DataSource Properties
     */
//    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.house")
    public DataSourceProperties houseDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Creates House DataSource based on House DataSource Properties
     */
//    @Primary
    @Bean(name = "houseDataSource")
    public DataSource houseDataSource() {
        return houseDataSourceProperties().initializeDataSourceBuilder().build();
    }

    /**
     * Configures the EntityManagerFactory for the House DataSource
     */
//    @Primary
    @Bean(name = "houseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean houseEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(houseDataSource());
        factory.setPackagesToScan("com.folau.datasources.house.entity"); // Entity package for house
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Add JPA properties
        factory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create"); // Auto-create schema
        factory.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // MySQL dialect
        return factory;
    }

    /**
     * Configures the TransactionManager for the House DataSource
     */
//    @Primary
    @Bean(name = "houseTransactionManager")
    public PlatformTransactionManager houseTransactionManager(@Autowired @Qualifier("houseEntityManagerFactory") LocalContainerEntityManagerFactoryBean houseEntityManagerFactory) {
        return new JpaTransactionManager(houseEntityManagerFactory.getObject());
    }
}