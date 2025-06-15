package ru.yandex.practicum.telemetry.collector.service.proto.handler;

import com.google.protobuf.MessageLite;
import ru.yandex.practicum.telemetry.collector.service.proto.KafkaEventProducerProto;

public abstract class BaseEventProtoHandler<T extends MessageLite> {
    protected final KafkaEventProducerProto producer;

    protected BaseEventProtoHandler(KafkaEventProducerProto producer) {
        this.producer = producer;
    }

    public abstract Enum<?> getMessageType();

    public abstract void handle(T event);
}
