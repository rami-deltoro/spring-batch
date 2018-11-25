package com.rami.config;

import com.rami.model.Customer;
import com.rami.rowmapper.CustomerRowMapper;
import com.rami.writer.MyItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {


    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyItemWriter myItemWriter;


    @Bean
    public JdbcCursorItemReader<Customer> cursorItemReader() {
        final JdbcCursorItemReader<Customer> jdbcCursorItemReader = new JdbcCursorItemReader();
        jdbcCursorItemReader.setSql("select id, firstName, lastName, birthdate from customer order by lastName, firstName");
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setRowMapper(new CustomerRowMapper());

        return jdbcCursorItemReader;
    }



    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer,Customer>chunk(10)
                .reader(cursorItemReader())
                .writer(myItemWriter)
                .build();
    }

    @Bean
    public Job interfacesJob() {
        return jobBuilderFactory.get("databaseJob")
                .start(step1())
                .build();
    }
}
