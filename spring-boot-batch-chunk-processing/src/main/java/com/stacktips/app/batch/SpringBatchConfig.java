package com.stacktips.app.batch;

import com.stacktips.app.model.Customer;
import com.stacktips.app.util.DateUtils;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .linesToSkip(1)
                .name("csvItemReader")
                .resource(new ClassPathResource("customers.csv"))
                .delimited()
                .delimiter(",")
                .names("index", "customerId", "firstName", "lastName", "company", "city",
                        "country", "phone1", "phone2",
                        "email", "subscriptionDate", "website")
                .fieldSetMapper(fieldSet -> Customer.builder()
                        .customerId(fieldSet.readString("customerId"))
                        .firstName(fieldSet.readString("firstName"))
                        .lastName(fieldSet.readString("lastName"))
                        .company(fieldSet.readString("company"))
                        .city(fieldSet.readString("city"))
                        .country(fieldSet.readString("country"))
                        .phone1(fieldSet.readString("phone1"))
                        .phone2(fieldSet.readString("phone2"))
                        .email(fieldSet.readString("email"))
                        .subscriptionDate(DateUtils.parseDate(fieldSet.readString("subscriptionDate")))
                        .website(fieldSet.readString("website"))
                        .build()).build();
    }

    @Bean
    public JpaItemWriter<Customer> writer(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<Customer> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Job csvImporterJob(Step customerStep, JobRepository jobRepository,
                              ImportJobListener importJobListener) {
        return new JobBuilder("csvImporterJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .validator(new JobParametersValidator() {
                    @Override
                    public void validate(JobParameters parameters) throws JobParametersInvalidException {
                        String ignoreCountry = parameters.getString("ignoreCountry");
                        if (ignoreCountry.equals("Costa Rica")) {
                            //TODO Validation logic goes here.
                        }
                    }
                })
                .listener(importJobListener)
                .flow(customerStep)
                .end()
                .build();
    }

    @Bean
    public Step csvImporterStep(
            ItemReader<Customer> csvReader, ItemWriter<Customer> csvWriter, CustomerJobProcessor processor,
            JobRepository jobRepository, PlatformTransactionManager tx) {

        return new StepBuilder("csvImporterStep", jobRepository)
                .<Customer, Customer>chunk(50, tx)
                .reader(csvReader)
                .writer(csvWriter)
                .processor(processor)
                .allowStartIfComplete(true)
                .build();
    }
}