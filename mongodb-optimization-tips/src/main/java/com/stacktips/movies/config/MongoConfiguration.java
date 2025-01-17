package com.stacktips.movies.config;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.WriteConcernResolver;

@Configuration
public class MongoConfiguration {

    @Bean
    public WriteConcernResolver writeConcernResolver() {
        return action -> {
            String entityName = action.getEntityType().getSimpleName();
            if (entityName.contains("Product")) {
                return WriteConcern.ACKNOWLEDGED;
            } else if (entityName.contains("Metadata")) {
                return WriteConcern.JOURNALED;
            }
            return action.getDefaultWriteConcern();

        };
    }

    @Bean
    public MongoClientSettingsBuilderCustomizer monoClientCustomizer() {
        return builder -> builder.readPreference(ReadPreference.nearest());
    }
}