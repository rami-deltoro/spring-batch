package com.rami.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatelessItemWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> data) throws Exception {
        data.forEach(item-> System.out.println("item="+item));
    }
}
