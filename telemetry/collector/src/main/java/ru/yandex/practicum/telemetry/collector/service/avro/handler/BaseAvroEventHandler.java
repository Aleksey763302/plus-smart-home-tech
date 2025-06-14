package ru.yandex.practicum.telemetry.collector.service.avro.handler;

import ru.yandex.practicum.telemetry.collector.model.device.BaseEvent;
import ru.yandex.practicum.telemetry.collector.service.BaseEventHandler;
import ru.yandex.practicum.telemetry.collector.service.EventSerializer;

public abstract class BaseAvroEventHandler <T, E extends BaseEvent>
        extends BaseEventHandler<T, E>
        implements AvroEventHandler<E>{
    public BaseAvroEventHandler(EventSerializer<T> serializer) {
        super(serializer);
    }
}
