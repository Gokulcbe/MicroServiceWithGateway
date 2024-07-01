package com.quinbay.reporting.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class OrderDTODeserializer implements Deserializer<OrderDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, OrderDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing OrderDTO", e);
        }
    }
}