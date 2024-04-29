package com.stacktips.app.config;

import com.stacktips.app.models.Customer;
import com.stacktips.app.utils.DateUtils;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public FlatFileItemReader<Customer> csvItemReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .linesToSkip(1)
                .name("csvItemReader")
                .resource(new ClassPathResource("customers.csv"))
                .delimited()
                .delimiter(",")
                .names("index","customerId","firstName","lastName","company","city","country",
                        "phone1","phone2","email","subscriptionDate","website")
                .fieldSetMapper(fieldSet -> {
                    return Customer.builder()
                            .customerId(fieldSet.readString("customerId"))
                            .firstName(fieldSet.readString("firstName"))
                            .lastName(fieldSet.readString("lastName"))
                            .company(fieldSet.readString("company"))
                            .city(fieldSet.readString("city"))
                            .country(fieldSet.readString("country"))
                            .phone1(fieldSet.readString("phone1"))
                            .phone2(fieldSet.readString("phone2"))
                            .email(fieldSet.readString("email"))
                            .website(fieldSet.readString("website"))
                            .subscriptionDate(DateUtils.convertDate(fieldSet.readString("subscriptionDate")))
                            .build();
                }).build();
    }

    public JpaItemWriter<Customer> writer(EntityManagerFactory emf) {
                new JpaItemWriterBuilder<Customer>()
                        .entityManagerFactory(emf)
    }


}
