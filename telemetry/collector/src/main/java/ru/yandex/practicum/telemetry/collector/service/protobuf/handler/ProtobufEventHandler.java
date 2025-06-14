package ru.yandex.practicum.telemetry.collector.service.protobuf.handler;

public interface ProtobufEventHandler<T extends com.google.protobuf.MessageLite> {
    Enum<?> getMessageType();
    void handle(T event);
}
