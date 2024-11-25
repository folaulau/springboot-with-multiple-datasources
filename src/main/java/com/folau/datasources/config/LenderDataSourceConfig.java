package com.folau.datasources.config;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = "com.folau.datasources.lender.repository",
        entityManagerFactoryRef = "lenderEntityManagerFactory",
        transactionManagerRef = "lenderTransactionManager"
)
public class LenderDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.lender")
    public DataSourceProperties lenderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource lenderDataSource() {
        return lenderDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean lenderEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("com.folau.datasources.lender.entity");
        factory.setDataSource(lenderDataSource());
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create");
        return factory;
    }

    @Bean
    public PlatformTransactionManager lenderTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(lenderDataSource());
        return transactionManager;
    }
}

