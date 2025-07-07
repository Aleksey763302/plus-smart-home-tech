package ru.yandex.practicum.kafka.serializer;

import com.google.protobuf.MessageLite;
import org.apache.kafka.common.serialization.Serializer;

public class GeneralProtobufSerializerVer2 implements Serializer<MessageLite> {
    @Override
    public byte[] serialize(String s, MessageLite data) {
        return data == null ? null : data.toByteArray();
    }
}
