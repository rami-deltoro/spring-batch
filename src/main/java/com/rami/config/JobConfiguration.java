package com.rami.config;

import com.rami.model.Customer;
import com.rami.reader.StatefulItemReader;
import com.rami.writer.CustomerItemWriter;
import com.rami.writer.StringItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private StringItemWriter stringItemWriter;



    @Bean
    public StatefulItemReader itemReader() {
        List<String> items = new ArrayList<>(100);

        for(int i=0;i<=100;i++) {
            items.add(String.valueOf(i));
        }

        return new StatefulItemReader(items);
    }




    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String,String>chunk(10)
                .reader(itemReader())
                .writer(stringItemWriter)
                .stream(itemReader())
                .build();
    }

    @Bean
    public Job interfacesJob() {
        return jobBuilderFactory.get("statefulJob")
                .start(step1())
                .build();
    }
}
