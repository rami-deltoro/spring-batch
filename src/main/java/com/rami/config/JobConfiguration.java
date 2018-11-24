package com.rami.config;

import com.rami.reader.StatelessItemReader;
import com.rami.writer.StatelessItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {


    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StatelessItemWriter statelessItemWriter;

    @Bean
    public StatelessItemReader statelessItemReader() {
        final List<String> data = new ArrayList<>();
        data.add("Rami");
        data.add("Del");
        data.add("Toro");

        return new StatelessItemReader(data);
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String,String>chunk(3)
                .reader(statelessItemReader())
                .writer(statelessItemWriter)
                .build();
    }

    @Bean
    public Job interfacesJob() {
        return jobBuilderFactory.get("interfacesJob")
                .start(step1())
                .build();
    }
}
