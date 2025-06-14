package ru.yandex.practicum.telemetry.collector.service.protobuf.handler;

import ru.yandex.practicum.telemetry.collector.service.EventSerializer;

public abstract class BaseProtobufEventHandler<T extends com.google.protobuf.MessageLite> implements ProtobufEventHandler<T> {
    protected final EventSerializer<T> serializer;

    protected BaseProtobufEventHandler(EventSerializer<T> serializer) {
        this.serializer = serializer;
    }

    public abstract Enum<?> getMessageType();

    @Override
    public void handle(T event) {
        serializer.send(event);
    }
}
