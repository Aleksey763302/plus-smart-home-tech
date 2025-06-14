package ru.yandex.practicum.telemetry.collector.service.avro.handler;

import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;

public interface AvroEventHandler<T extends BaseEvent> {
    Enum<?> getMessageType();

    void handle(T event);
}
