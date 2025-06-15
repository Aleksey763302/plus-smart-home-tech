package ru.yandex.practicum.kafka.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;

public class GeneralProtobufSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(String topic, Object data) {
        if (data == null) {
            return null;
        }
        try {
            return ((com.google.protobuf.Message)data).toByteArray();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
