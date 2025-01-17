package com.stacktips.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    String customerId;
    String firstName;
    String lastName;
    String company;
    String city;
    String country;
    String phone1;
    String phone2;
    String email;
    LocalDate subscriptionDate;
    String website;
}
