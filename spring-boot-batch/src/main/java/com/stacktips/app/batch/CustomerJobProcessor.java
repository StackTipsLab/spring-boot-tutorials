package com.stacktips.app.batch;

import com.stacktips.app.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@StepScope
@Component
public class CustomerJobProcessor implements ItemProcessor<Customer, Customer> {

    private final String ignoreCountry;

    public CustomerJobProcessor(@Value("#{jobParameters['ignoreCountry']}") String ignoreCountry) {
        this.ignoreCountry = ignoreCountry;
    }

    @Override
    public Customer process(Customer customer) {
        if (customer.getCountry().equalsIgnoreCase(ignoreCountry)) {
            log.info("Ignoring customer {} {} belongs to country {}", customer.getFirstName(),
                    customer.getLastName(), customer.getCountry());
            return null;
        }
        return customer;
    }
}
