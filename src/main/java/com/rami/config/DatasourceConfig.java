package com.rami.config;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {


    @Bean
    @ConfigurationProperties(prefix = "datasource.batch")
    public DataSource datasource() {
        System.out.println("creating dataosurce");
        return DataSourceBuilder.create().build();
    }

/*    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository myJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDatabaseType(DatabaseType.H2.getProductName());
        factory.setDataSource(dataSource());
        factory.setTransactionManager(transactionManager());
        return factory.getObject();
    }*/

}
