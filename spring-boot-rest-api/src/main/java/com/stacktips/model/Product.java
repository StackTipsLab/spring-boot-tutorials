package com.stacktips.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    Double price;
    String description;

    @JsonProperty("is_in_stock")
    boolean inStock;

    @JsonProperty("is_active")
    boolean active;

    @JsonProperty("units_available")
    Long unitsAvailable;

    @CreationTimestamp
    @JsonProperty("created_date")
    ZonedDateTime createdDate;

    @UpdateTimestamp
    @JsonProperty("updated_date")
    ZonedDateTime updatedDate;
}
