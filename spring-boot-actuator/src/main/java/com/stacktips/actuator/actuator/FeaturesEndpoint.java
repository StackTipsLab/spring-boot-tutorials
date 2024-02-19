package com.stacktips.actuator.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "foo-bar")
public class FeaturesEndpoint {

    @ReadOperation
    public Map<String, String> readOperation() {
        return Map.of("foo", "bar");
    }

    @WriteOperation
    public Map<String, String> writeOperation(String param1, String param2) {
        return Map.of("param1", param1, "param2", param2);
    }

    @DeleteOperation
    public Map<String, Boolean> deleteOperation() {
        return Map.of("success", true);
    }
}