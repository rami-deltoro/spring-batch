package com.rami.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {


    @Bean
    @ConfigurationProperties(prefix = "datasource.batch")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }


}
