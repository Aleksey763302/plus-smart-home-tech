package ru.yandex.practicum.telemetry.collector.service.proto.handler;

import com.google.protobuf.MessageLite;
import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.telemetry.collector.service.proto.KafkaEventProducerProto;

@RequiredArgsConstructor
public abstract class BaseEventProtoHandler<T extends MessageLite> {
    protected final KafkaEventProducerProto producer;

    public abstract Enum<?> getMessageType();

    public void handle(T event) {
        producer.send(event);
    }
}
