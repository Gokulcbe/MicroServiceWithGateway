package com.quinbay.orders.dao.entity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.orders.dto.OrderDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class OrderDTOSerializer implements Serializer<OrderDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, OrderDTO data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing OrderDTO", e);
        }
    }
}