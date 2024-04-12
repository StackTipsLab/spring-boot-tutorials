package com.stacktips.app.batch;

import com.stacktips.app.model.Customer;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class CustomerLoader {

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public Job csvImporterJob(Step customerStep, JobCompletionListener listener,
            JobRepository jobRepository) {
        return new JobBuilder("csvImporterJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener).flow(customerStep).end()
                .build();
    }

    @Bean
    public Step customerStep(ItemReader<Customer> csvReader, ItemWriter<Customer> csvWriter,
            ItemProcessor<Customer, Customer> processor,
            JobRepository jobRepository,
            JobCompletionListener jobCompletionListener,
            PlatformTransactionManager ptm,
            @Qualifier("taskExecutor") TaskExecutor taskExecutor) {

        return new StepBuilder("customerStep", jobRepository)
                .<Customer, Customer>chunk(2, ptm)
                .reader(csvReader)
                .processor(processor)
                .listener(jobCompletionListener)
                .writer(csvWriter)
                .taskExecutor(taskExecutor)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .linesToSkip(1)
                .name("csvItemReader")
                .resource(new ClassPathResource("customers.csv"))
                .delimited()
                .delimiter(",")
                .names("index", "customerId", "firstName", "lastName", "company", "city", "country", "phone1", "phone2",
                        "email", "subscriptionDate", "website")

                //TODO exactly match
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                    setTargetType(Customer.class);
//                }})
                .fieldSetMapper(fieldSet ->
                                Customer.builder()
                                        .customerId(fieldSet.readString("customerId"))
                                        .firstName(fieldSet.readString("firstName"))
                                        .lastName(fieldSet.readString("lastName"))
                                        .company(fieldSet.readString("company"))
                                        .city(fieldSet.readString("city"))
                                        .country(fieldSet.readString("country"))
                                        .phone1(fieldSet.readString("phone1"))
                                        .phone2(fieldSet.readString("phone2"))
                                        .email(fieldSet.readString("email"))
//                                .subscriptionDate(fieldSet.readString("subscriptionDate"))
                                        .website(fieldSet.readString("website"))
                                        .build()
                )
                .build();
    }

    @Bean
    public JpaItemWriter<Customer> writer(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<Customer> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
}