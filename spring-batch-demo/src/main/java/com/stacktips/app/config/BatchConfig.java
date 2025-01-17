package com.stacktips.app.config;

import com.stacktips.app.model.Customer;
import com.stacktips.app.utils.DateUtils;
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
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .linesToSkip(1)
                .name("csvItemReader")
                .resource(new ClassPathResource("customers.csv"))
                .delimited()
                .delimiter(",")
                .names("index", "customerId", "firstName", "lastName", "company",
                        "city", "country", "phone1",
                        "phone2", "email", "subscriptionDate", "website")
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
                            .subscriptionDate(DateUtils.parseDate(fieldSet.readString("subscriptionDate")))
                            .build();
                })
                .build();

    }


    @Bean
    public JpaItemWriter<Customer> writer(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Customer>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step csvImporterStep(ItemReader<Customer> reader, ItemWriter<Customer> writer,
                                JobRepository jobRepository, PlatformTransactionManager transactionManager,
                                CustomJobProcessor processor) {
        return new StepBuilder("csvImporterStep", jobRepository)
                .<Customer, Customer>chunk(50, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job csvImporterJob(Step csvImporterStep, JobRepository jobRepository, ImportJobListener listener) {
        return new JobBuilder("csvImporterJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .validator(new JobParametersValidator() {
                    @Override
                    public void validate(JobParameters parameters) throws JobParametersInvalidException {
                        if ("Costa Rica".equals(parameters.getString("ignoreCountry"))) {
                            throw new JobParametersInvalidException("Invalid configuration");
                        }
                    }
                })
                .listener(listener)
                .flow(csvImporterStep)
                .end()
                .build();
    }

}
