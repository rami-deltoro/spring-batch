package com.rami.writer;

import com.rami.model.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyItemWriter implements ItemWriter<Customer> {
    @Override
    public void write(List<? extends Customer> list) throws Exception {
        list.forEach(customer -> System.out.println("Customer="+customer));
    }
}
