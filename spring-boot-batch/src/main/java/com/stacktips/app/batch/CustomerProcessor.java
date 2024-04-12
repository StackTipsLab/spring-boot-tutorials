package com.stacktips.app.batch;

import com.stacktips.app.model.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) {
        if (customer.getCustomerId() == null) {
            return null;
        }
        return customer;
    }
}
