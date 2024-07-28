package com.stacktips.movies.repository;

import com.stacktips.movies.dto.TotalSales;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalesRepository {

    private final MongoTemplate mongoTemplate;

    public List<TotalSales> calculateLastMonthSales() {
        MatchOperation matchStage = Aggregation.match(Criteria.where("date")
                .gte(getLastMonthStartDate())
                .lte(new Date()));

        LookupOperation lookupProduct = Aggregation.lookup(
                "Product", "productId", "_id", "product");
        LookupOperation lookupPrice = Aggregation.lookup(
                "Price", "productId", "_id", "price");
        UnwindOperation unwindProduct = Aggregation.unwind("product");
        UnwindOperation unwindPrice = Aggregation.unwind("price");

        GroupOperation groupStage = Aggregation.group("product.name")
                .sum(ArithmeticOperators.Multiply.valueOf("quantity")
                        .multiplyBy("price.price"))
                .as("totalSales");

        ProjectionOperation projectStage = Aggregation.project()
                .andExpression("_id").as("productName")
                .andExpression("totalSales").as("totalSales")
                .andExclude("_id");

        Aggregation aggregation = Aggregation.newAggregation(matchStage,
                lookupProduct,
                unwindProduct,
                lookupPrice,
                unwindPrice,
                groupStage,
                projectStage);
        AggregationResults<TotalSales> results = mongoTemplate.aggregate(
                aggregation, "Sales", TotalSales.class);
        return results.getMappedResults();
    }

    private Date getLastMonthStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
