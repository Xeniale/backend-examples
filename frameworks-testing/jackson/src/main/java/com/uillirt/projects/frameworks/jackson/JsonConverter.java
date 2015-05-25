package com.uillirt.projects.frameworks.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by kshekhovtsova on 25.05.2015.
 */
public class JsonConverter<T> {

    private final ObjectMapper mapper = new ObjectMapper();

    public T fromJson(String attributes) throws RuntimeException {
        if (attributes == null || attributes.isEmpty()) {
            throw new IllegalArgumentException("Attribute can't be empty or null");
        }
        try {
            return mapper.readValue(attributes, new TypeReference<T>() {});
        }
        catch (IOException e) {
            throw new RuntimeException("Error occurred while creating attribute", e);
        }
    }
}
