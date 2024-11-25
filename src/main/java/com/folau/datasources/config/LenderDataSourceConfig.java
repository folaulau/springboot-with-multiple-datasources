package com.folau.datasources.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        basePackages = "com.folau.datasources.lender.repository", // Repository package for lender
        entityManagerFactoryRef = "lenderEntityManagerFactory",  // Reference to EntityManagerFactory
        transactionManagerRef = "lenderTransactionManager"       // Reference to TransactionManager
)
public class LenderDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.lender")
    public DataSourceProperties lenderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "lenderDataSource")
    public DataSource lenderDataSource() {
        return lenderDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "lenderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean lenderEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(lenderDataSource());
        factory.setPackagesToScan("com.folau.datasources.lender.entity"); // Entity package for lender
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create"); // Auto-creation of tables
        return factory;
    }

    @Bean(name = "lenderTransactionManager")
    public PlatformTransactionManager lenderTransactionManager(@Autowired @Qualifier("lenderEntityManagerFactory") LocalContainerEntityManagerFactoryBean lenderEntityManagerFactory) {
        return new JpaTransactionManager(lenderEntityManagerFactory.getObject()); // Link transaction manager to EntityManagerFactory
    }
}