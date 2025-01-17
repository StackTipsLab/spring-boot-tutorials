package com.stacktips.app.config;

import com.stacktips.app.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@JobScope
@Component
public class CustomJobProcessor implements ItemProcessor<Customer, Customer> {

    private final String ignoreCountry;

    public CustomJobProcessor(@Value("#{jobParameters['ignoreCountry']}") String ignoreCountry) {
        this.ignoreCountry = ignoreCountry;
    }

    @Override
    public Customer process(Customer item) throws Exception {
        if (item.getCountry().equals(ignoreCountry)) {
            log.info("Customer ignored: {}", item.toString());
        }
        return item;
    }
}
